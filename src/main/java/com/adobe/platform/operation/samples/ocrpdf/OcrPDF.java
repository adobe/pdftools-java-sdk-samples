/*
 * Copyright 2019 Adobe
 * All Rights Reserved.
 *
 * NOTICE: Adobe permits you to use, modify, and distribute this file in
 * accordance with the terms of the Adobe license agreement accompanying
 * it. If you have received this file from a source other than Adobe,
 * then your use, modification, or distribution of it requires the prior
 * written permission of Adobe.
 */

package com.adobe.platform.operation.samples.ocrpdf;

import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.adobe.platform.operation.ExecutionContext;
import com.adobe.platform.operation.auth.Credentials;
import com.adobe.platform.operation.exception.SdkException;
import com.adobe.platform.operation.exception.ServiceApiException;
import com.adobe.platform.operation.exception.ServiceUsageException;
import com.adobe.platform.operation.io.FileRef;
import com.adobe.platform.operation.pdfops.OCROperation;

/**
 * This sample illustrates how to perform OCR operation on a PDF file and convert it into a searchable PDF file.
 * <p>
 * Note that OCR operation on a PDF file results in a PDF file.
 * <p>
 * Refer to README.md for instructions on how to run the samples.
 */
public class OcrPDF {

    // Initialize the logger.
    private static final Logger LOGGER = LoggerFactory.getLogger(OcrPDF.class);

    public static void main(String[] args) {

        try {

            // Initial setup, create credentials instance.
            Credentials credentials = Credentials.serviceAccountCredentialsBuilder()
                    .fromFile("pdftools-api-credentials.json")
                    .build();

            //Create an ExecutionContext using credentials and create a new operation instance.
            ExecutionContext executionContext = ExecutionContext.create(credentials);
            OCROperation ocrOperation = OCROperation.createNew();

            // Set operation input from a source file.
            FileRef source = FileRef.createFromLocalFile("src/main/resources/ocrInput.pdf");
            ocrOperation.setInput(source);

            // Execute the operation
            FileRef result = ocrOperation.execute(executionContext);

            // Save the result at the specified location
            result.saveAs("output/ocrOutput.pdf");

        } catch (ServiceApiException | IOException | SdkException | ServiceUsageException ex) {
            LOGGER.error("Exception encountered while executing operation", ex);
        }
    }
}