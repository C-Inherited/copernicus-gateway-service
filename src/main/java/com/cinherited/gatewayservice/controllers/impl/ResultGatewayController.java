package com.cinherited.gatewayservice.controllers.impl;

import com.cinherited.gatewayservice.clients.ResultClient;
import com.cinherited.gatewayservice.controllers.interfaces.IResultController;
import com.cinherited.gatewayservice.dtos.RequestDTO;
import com.cinherited.gatewayservice.enums.Status;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/copernicus")
@RestController
public class ResultGatewayController implements IResultController {

    @Autowired
    ResultClient resultClient;

    private static String resultAuthOk;

    @GetMapping("/opportunity-status/{id}")
    public Status getResult(@PathVariable Integer id) {
        return resultClient.getResult(id, "Bearer " + getResultAuthOk());
    }

    @PostMapping("/opportunity-status/{id}")
    public Integer changeStatus(@RequestBody RequestDTO requestDTO) {
        return resultClient.changeStatus(requestDTO, "Bearer " + getResultAuthOk());
    }

    public static String getResultAuthOk() {
        return resultAuthOk;
    }

    public static void setResultAuthOk(String resultAuthOk) {
        ResultGatewayController.resultAuthOk = resultAuthOk;
    }
}
