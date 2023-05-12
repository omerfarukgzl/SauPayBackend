package com.saupay.domainservice.response;

import com.fasterxml.jackson.annotation.JsonUnwrapped;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Response<T> {
       /* @JsonUnwrapped*/
        private T data;
        private Status status;

        public Response(T data) {
            this.data = data;
            this.status = new Status(true, null, null);
        }

}


