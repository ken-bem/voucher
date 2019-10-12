package com.rumroute.voucher;

import com.rumroute.model.voucher.Voucher;
import com.rumroute.user.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.security.Principal;

@Controller
@AllArgsConstructor
public class VoucherController {

    private final VoucherService service;
    private final UserService uservice;
    private final ApplicationEventPublisher publisher;

    @PostMapping(value = "/api/voucher/{id}/redeem")
    public String voucher(@PathVariable("id") Long id,@Valid Voucher voucher, Model model) {

        return "";
    }


    @RequestMapping(value = "history", method = RequestMethod.GET)
    public String voucherHistory(Model model, Principal principal){

        return "drink/voucher-history";
    }

    @RequestMapping(value = "/voucher/{id}", method = RequestMethod.GET)
    public String voucherList(Model model, @PathVariable("id") int id, Principal principal) {

        return "redirect:/routes/";

    }


    @PostMapping(value = "/api/validateVoucher")
    public ResponseEntity<?> voucherValidate(@RequestBody String json, Model model){
        return ResponseEntity.ok().build();
    }


}
