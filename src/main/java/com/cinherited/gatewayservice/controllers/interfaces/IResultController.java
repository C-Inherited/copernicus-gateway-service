package com.cinherited.gatewayservice.controllers.interfaces;

import com.cinherited.gatewayservice.dtos.RequestDTO;
import com.cinherited.gatewayservice.enums.Status;

public interface IResultController {

    Status getResult(Integer id);

    Integer changeStatus(RequestDTO requestDTO);

}
