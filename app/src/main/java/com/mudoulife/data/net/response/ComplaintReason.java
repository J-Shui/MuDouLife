package com.mudoulife.data.net.response;

public class ComplaintReason {
    private String reason;

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    @Override
    public String toString() {
        return "ComplaintReason{" +
                "reason='" + reason + '\'' +
                '}';
    }
}
