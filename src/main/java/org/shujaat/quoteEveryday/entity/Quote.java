package org.shujaat.quoteEveryday.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Quote {

    @JsonProperty("q")
    private String quote;

    @JsonProperty("a")
    private String author;

    @JsonProperty("i")
    private String image;

    @JsonProperty("c")
    private Integer characterCount;

    @JsonProperty("h")
    private String preformattedHTML;
}
