package it.unisalento.se.common;

public class Constants {

    // Constants for type of users
    public static final String STUDENT = "STUDENT";
    public static final String PROFESSOR = "PROFESSOR";
    public static final String SECRETARIAT = "SECRETARIAT";
    public static final Integer STUDENT_VALUE = 1;
    public static final Integer PROFESSOR_VALUE = 2;
    public static final Integer SECRETARIAT_VALUE = 3;

    // States of reported problems
    public static final String RECEIVED = "RECEIVED";
    public static final String IN_PROGRESS = "IN_PROGRESS";
    public static final String SOLVED = "SOLVED";
    public static final String REFUSED = "REFUSED";


    public static final String DOCUMENT = "DOCUMENT";
    public static final String LESSON = "LESSON";


    // Options for Multipart configurations
    public static final String TMP_SAVE_LOCATION = System.getProperty("java.io.tmpdir"); // Temporary location where files will be stored
    public static final long MAX_FILE_SIZE = 5242880; // 5MB : Max file size.
    public static final long MAX_REQUEST_SIZE = 20971520; // 20MB : Total request size containing Multi part.
    public static final int FILE_SIZE_THRESHOLD = 0; // Size threshold after which files will be written to disk
    public static final String FINAL_SAVE_LOCATION = "./upload";
}
