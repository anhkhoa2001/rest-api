package org.example.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Date;

@Data
@AllArgsConstructor
public class RequestTimer {

    private Date start;
    private Date end;
}
