package com.devpaik.nss.simulation;



public class NotificationSender {

    public SendResponse send(final ResponseTypeSelector selector) {
        final ResponseType responseType = selector.getChoiceResponseType();

        try {
            Thread.sleep(responseType.getLatencyType().getMilliSecond());
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        if ( responseType.getResultType().equals(ResultType.EXCEPTION) ) {
            throw new RuntimeException("알 수 없는 런타임 오류 발생");
        }

        return new SendResponse(responseType.getResultType().getResultCode());
    }
}
