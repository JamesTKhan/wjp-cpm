package com.jpcodes.message;

/**
 * Error Message Enum with added support for having dynamic/templated messages
 */
public enum ErrorMessageEnum {
    CAR_NOT_FOUND("Car with ticket number %s is not in the parking lot."),
    GARAGE_FULL("Parking Garage is full! No space to fulfill request."),
    TICKET_NOT_INTEGER("Ticket number was not an integer value: %s"),
    INVALID_PREFIX("An invalid prefix value was provided: %s"),
    PROP_FILE_NOT_FOUND("Properties file was not found."),
    PROP_NOT_FOUND("No property found with key %s"),
    ERR_READ_FILE("Error while reading properties file.");

    private final String description;

    ErrorMessageEnum(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }

    /**
     * Get Description that contains a templated message
     *
     * @param param parameter to use for String.format()
     * @return message with formatted parameter
     */
    public String getDescription(String param) {
        return String.format(description, param);
    }

}