package com.itis.kikoff;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@Builder
@NoArgsConstructor
public class DataBean {
    private String head;
    private String module;
    private String actual;
    private String expected;

    public DataBean(String head, String module,
                    String actual, String expected) {
        super();
        this.head = head;
        this.module = module;
        this.actual = actual;
        this.expected = expected;
    }

}
