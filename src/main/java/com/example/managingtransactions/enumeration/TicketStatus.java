package com.example.managingtransactions.enumeration;

public enum TicketStatus {
    STATUS_NEW("STATUS_NEW"),
    STATUS_IN_PROGRESS("STATUS_IN_PROGRESS"),
    STATUS_IN_REVIEW("STATUS_IN_REVIEW"),
    STATUS_FINISHED("STATUS_IN_REVIEW");

    private String  status;

    TicketStatus(String status) {
        this.status = status;
    }

    public String getTicketStatus(){
        return this.status;
    }

}
