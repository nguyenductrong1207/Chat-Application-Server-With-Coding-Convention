package application.talk.domains;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Request extends BaseEntity {
    private User _requester;
    private PrivateGroup _privateGroup;
    private LocalDateTime _requestTimestamp;
    private RequestStatus _requestStatus;

    public enum RequestStatus {
        APPROVE, REJECT, WAITING
    }

    public Request(User _requester, PrivateGroup _privateGroup, LocalDateTime requestTimestamp, RequestStatus requestStatus) {
        this._requester = _requester;
        this._privateGroup = _privateGroup;
        this._requestTimestamp = requestTimestamp;
        this._requestStatus = requestStatus;
        this.formatDateTime();
    }

    public void formatDateTime() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm dd-MM-yyyy");
        _requestTimestamp.format(formatter);
    }

    public User getRequester() {
        return _requester;
    }

    public void setRequester(User requester) {
        this._requester = requester;
    }

    public PrivateGroup getPrivateGroup() {
        return _privateGroup;
    }

    public LocalDateTime getRequestTime() {
        return _requestTimestamp;
    }

    public RequestStatus getRequestStatus() {
        return _requestStatus;
    }

    public void setRequestStatus(RequestStatus requestStatus) {
        this._requestStatus = requestStatus;
    }
}
