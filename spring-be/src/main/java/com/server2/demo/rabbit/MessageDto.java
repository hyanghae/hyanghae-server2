package com.server2.demo.rabbit;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class MessageDto {

    String title;
    String content;
}
