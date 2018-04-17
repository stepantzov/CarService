package com.mycarservice.controller;

import com.mycarservice.dto.CarDto;
import com.mycarservice.entity.CarEntity;
import com.mycarservice.service.CarService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/car")
@RestController
public class CarController {

    @Autowired
    private CarService carService;

    @RequestMapping(method = RequestMethod.GET, value = "/controllerOutput")
    public String doOutput() {
        return "CarController output.";
    }

    @RequestMapping(method = RequestMethod.GET, value = "/getCarAttributes")
    public List<CarDto> getCarAttributes() {
        return carService.getCarInstances();
    }

    @RequestMapping(method = RequestMethod.POST, value = "/insertCar")
    @ApiOperation(value = "Create a new Car Instance.")
    @ApiResponses(value = {@ApiResponse(code = 201, message = "New Car instance added successfully."),
            @ApiResponse(code = 500, message = "Failed to add new Car instance.")})
    @ResponseStatus(value = HttpStatus.CREATED, reason = "New Car instance added successfully.")
    public ResponseEntity addCarAttributes(@RequestBody CarDto carDto) {
        try {
            carService.addCarInstance(carDto);
            return new ResponseEntity(HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @RequestMapping(method = RequestMethod.GET, value = "/getCarEntitiesFromDatabase")
    public List<CarEntity> getCarEntities() {
        return carService.getCarEntitiesFromDatabase();
    }

    @RequestMapping(method = RequestMethod.POST, value = "/insertCarEntityToDatabase")
    @ApiOperation(value = "Create a new Car Entity in database.")
    @ApiResponses(value = {@ApiResponse(code = 201, message = "New Car entity added successfully to database."),
            @ApiResponse(code = 500, message = "Failed to add new Car instance.")})
    @ResponseStatus(value = HttpStatus.CREATED, reason = "New Car instance added successfully to database.")
    public ResponseEntity addCarAttributes() {
        try {
            carService.addCarEntityToDatabase();
            return new ResponseEntity(HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}