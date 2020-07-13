package com.nousix.coruscant.controller.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.net.InetAddress;
import java.util.List;

public class GreetingDTO {

    String word;
    String name;

    GreetingDTO brother;
    String url;

    String hostname;

    List<String> secrets;

    public GreetingDTO() {}
    public GreetingDTO(String word, String name, List<String> secrets, String hostname,GreetingDTO brother) {
        this.word = word;
        this.name = name;
        this.secrets = secrets;
        this.brother = brother;
        this.hostname = hostname;
    }

    @JsonProperty("message")
    public String say() {
        return word + " " + name;
    }

    public String getWord() {
        return word;
    }

    public void setWord(String say) {
        this.word = say;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<String> getSecrets() {
        return secrets;
    }

    public void setSecrets(List<String> secrets) {
        this.secrets = secrets;
    }

    public GreetingDTO getBrother() {
        return brother;
    }

    public void setBrother(GreetingDTO brother) {
        this.brother = brother;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getHostname() {
        return hostname;
    }

    public void setHostname(String hostname) {
        this.hostname = hostname;
    }
}
