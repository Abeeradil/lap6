package org.example.lap6.Api;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ApiResponse {
    public ApiResponse() {
    }

    private String message;
}
