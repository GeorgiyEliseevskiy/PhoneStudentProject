package com.example.phonestudentproject.model.DTO;

import com.example.phonestudentproject.model.entity.Phone;
import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class ConversationDTO {

    private String phoneNumberFrom;
    private String phoneNumberTo;

    private Phone phoneFrom;
    private Phone phoneTo;
}
