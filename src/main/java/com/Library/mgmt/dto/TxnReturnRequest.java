package com.Library.mgmt.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class TxnReturnRequest {

    @NotBlank
    private String bookNo;

    @NotBlank
    private String txnId;

    @NotBlank
    private String userEmail;

}
