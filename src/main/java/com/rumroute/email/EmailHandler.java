package com.rumroute.email;

import com.rumroute.dao.UserCRUD.UserService;
import com.rumroute.model.voucher.Voucher;
import com.rumroute.model.user.User;
import okhttp3.*;
import org.apache.tomcat.util.codec.binary.Base64;
import org.joda.time.DateTime;
import org.joda.time.DateTimeZone;
import org.joda.time.Interval;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

@SuppressWarnings("Duplicates")
@Component
@PropertySource("classpath:wordpress.properties")
public  class EmailHandler {


    @Autowired
    private UserService service;

    @Value("${server.url}")
    private String server_url;

    private static final MediaType JSON =
            MediaType.parse("application/json; charset=utf-8");
    private  String api = "1d4df525b2";
    private OkHttpClient client = new OkHttpClient();

    public void voucherHandler(Voucher voucher) throws Exception {

        String route = "forms/2/submissions";
        String method = "POST";

        String url = generateUrl(route, method);

        SimpleDateFormat dateFormat = new SimpleDateFormat(" hh:mm:ss dd-MM-yyyy");
        String register = dateFormat.format(voucher.getRedeemdate());



        JSONObject data = new JSONObject();
        data.put("input_1", voucher.getUser().getUsername());
        data.put("input_9",  voucher.getEmail());
        data.put("input_3", voucher.getSpot());
        data.put("input_5", voucher.getShot());
        data.put("input_2", register);
        data.put("input_4", voucher.getRum());
        data.put("input_10", voucher.getUser().getEmail());

        JSONObject send = new JSONObject();
        send.put("input_values", data);

        System.out.println(send);

        sendEmail(send, url);
    }

    public void registrationHandler(User user) throws Exception {


        String route = "forms/1/submissions";
        String method = "POST";

        String url = generateUrl(route, method);
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
        String userBirthday = dateFormat.format(user.getBirthdate());
        String register = dateFormat.format(user.getRegisterDate());

        JSONObject data = new JSONObject();
        data.put("input_1", user.getName());
        data.put("input_9", userBirthday);
        data.put("input_3", user.getEmail());
        data.put("input_4", user.getCountry());
        data.put("input_5", user.getState());
        data.put("input_6", user.getUsername());
        data.put("input_10", user.getPhone());
        data.put("input_8", register);

        JSONObject send = new JSONObject();
        send.put("input_values", data);

       sendEmail(send, url);

    }

    public void lostPasswordHandler() {
    }

    public void testEmail() throws Exception {
        String route = "forms/4/submissions";
        String method = "POST";

        String url = generateUrl(route, method);

        JSONObject data = new JSONObject();
        data.put("input_1","Kenneth");


        JSONObject send = new JSONObject();
        send.put("input_values", data);

        System.out.println(send);
        RequestBody requestBody = RequestBody.create(JSON, send.toJSONString());


        Request request = new Request.Builder()
                .url(url)
                .post(requestBody)
                .build();

        Response response = client.newCall(request).execute();
        String value = response.body().string();
        System.out.println(value);
    }

    public JSONObject feedbackHandler(String json, String user) throws Exception{
        String route = "forms/3/submissions";
        String method = "POST";

        String url = generateUrl(route, method);
        SimpleDateFormat dateFormat = new SimpleDateFormat(" hh:mm:ss dd-MM-yyyy");
        String submited = dateFormat.format(new Date());

        JSONParser parser = new JSONParser();
        JSONObject rating = (JSONObject) parser.parse(json);


        JSONObject data = new JSONObject();
        data.put("input_1", user );
        data.put("input_2", rating.get("spot").toString());
        data.put("input_4", rating.get("comment").toString());
        data.put("input_6", submited);

        JSONObject send = new JSONObject();
        send.put("input_values", data);

        System.out.println(send);
        RequestBody requestBody = RequestBody.create(JSON, send.toJSONString());


        Request request = new Request.Builder()
                .url(url)
                .post(requestBody)
                .build();

        Response response = client.newCall(request).execute();
        String value = response.body().string();
        System.out.println(value);


        return (JSONObject) parser.parse(value);
    }

    public JSONObject rateExperienceHandler(String json, String user) throws Exception{

        String route = "forms/4/submissions";
        String method = "POST";

        String url = generateUrl(route, method);
        SimpleDateFormat dateFormat = new SimpleDateFormat(" hh:mm:ss dd-MM-yyyy");
        String submited = dateFormat.format(new Date());

        JSONParser parser = new JSONParser();
        JSONObject rating = (JSONObject) parser.parse(json);


        JSONObject data = new JSONObject();
        data.put("input_1", user);
        data.put("input_2", rating.get("comment").toString() );
        data.put("input_3", rating.get("rating").toString());
        data.put("input_4", submited);

        JSONObject send = new JSONObject();
        send.put("input_values", data);

        System.out.println(send);
        RequestBody requestBody = RequestBody.create(JSON, send.toJSONString());


        Request request = new Request.Builder()
                .url(url)
                .post(requestBody)
                .build();

        Response response = client.newCall(request).execute();
        String value = response.body().string();
        System.out.println(value);


        return (JSONObject) parser.parse(value);
    }

    public void thankEmail(User user) throws Exception{


        String route = "forms/6/submissions";
        String method = "POST";

        String url = generateUrl(route, method);

        JSONObject data = new JSONObject();
        data.put("input_1", user.getUsername());
        data.put("input_2", user.getEmail());

        JSONObject send = new JSONObject();
        send.put("input_values", data);

        sendEmail(send, url);

    }








    //Email URL Generators
    public String signatureCalc(String route, String method) throws Exception {

        String secret_key = "051fd0b350cfe41";

        Mac crypt = Mac.getInstance("HmacSha1");
        SecretKeySpec secret = new SecretKeySpec(secret_key.getBytes("UTF-8"), "HmacSha1");
        crypt.init(secret);


        int expires = getExpiration();


        String format = api + ":" + method + ":" + route + ":" + expires;

        return Base64.encodeBase64String(crypt.doFinal(format.getBytes("UTF-8")));

    }

    private int getExpiration() {

        return (int) (System.currentTimeMillis() / 1000L) + 60;
    }

    private  String generateUrl(String route, String method) throws Exception {

        String base_url = server_url + "/gravityformsapi/";

        return base_url + route + "?api_key=" + api + "&signature="
                + signatureCalc(route, method) + "&expires=" + getExpiration();
    }

    //Send Emails
    private void sendEmail(JSONObject send, String url ){

        RequestBody requestBody = RequestBody.create(JSON, send.toJSONString());

        Request request = new Request.Builder()
                .url(url)
                .post(requestBody)
                .build();

        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                e.printStackTrace();
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                System.out.println(response.body().string());
                response.close();
            }
        });
    }

    @Scheduled(cron = "0 0 */1 * * ?")
    public void unUsedVoucherHandler() {
        System.out.println("The Cron TasK is running");
        DateTimeZone timezone  = DateTimeZone.forID("America/Puerto_Rico");
        DateTime now = new DateTime(timezone);
        Interval timeLimitCheck = new Interval(now.minusHours(24), now);

        Iterable<User> users =  service.findAll();
        users.forEach(e->{
            DateTime registered = new DateTime(e.getRegisterDate(), timezone);
            DateTime last = new DateTime(e.getLastRedeem());
            boolean isInTimeLimit = timeLimitCheck.contains(registered);

            if(!isInTimeLimit && e.getCredits() == 3 && !e.isHasRedeemed()){
                try {
                    send24hourEMAIL(e, "Unused");
                    e.setHasRedeemed(true);
                    service.save(e);
                }
                catch (Exception e1) {
                    e1.printStackTrace();
                }

            }

            else if(last.plusHours(24).isAfterNow() && e.getCredits() < 3 && e.isSendReminder()){
                try {
                    send24hourEMAIL(e, "Voucher");
                    e.setSendReminder(false);
                    service.save(e);

                } catch (Exception e1) {
                    e1.printStackTrace();
                }
            }


        });
    }

    private void send24hourEMAIL(User user, String type) throws Exception {

        String route = "forms/7/submissions";
        String method = "POST";

        String url = generateUrl(route, method);

        JSONObject data = new JSONObject();
        data.put("input_1", user.getUsername());
        data.put("input_2", user.getEmail());

        JSONObject send = new JSONObject();
        send.put("input_values", data);

        sendEmail(send, url);
        System.out.printf(" The type of email is %s and the user is %s \n", type, user.getName());
    }


}
