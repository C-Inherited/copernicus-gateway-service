package com.cinherited.gatewayservice.controllers.interfaces;

import com.cinherited.gatewayservice.dtos.RequestDTO;
import com.cinherited.gatewayservice.enums.Status;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

public interface IResultController {

    Status getResult(Integer id);

    Integer changeStatus(RequestDTO requestDTO);

}
