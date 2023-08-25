package com.oli.booking.utility;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.Security;
import java.util.UUID;

import org.bouncycastle.jce.provider.BouncyCastleProvider;

import com.oli.booking.models.Booking;
import com.oli.booking.models.response.CreatedBooking;

public class TransactionMock {
    int chanId = 5;
    String contractAddress = "";
    String contractABIPath = "";

    // TODO: change it call actual smartcontract on goerli testnet
    public static CreatedBooking saveBookingOnGoerli(Booking booking) throws NoSuchAlgorithmException {
        Security.addProvider(new BouncyCastleProvider());
        String hash = getBookingHash(booking);
        String transactionId = UUID.randomUUID().toString();
        CreatedBooking createdBooking = new CreatedBooking(booking.getBookingId(), transactionId, hash);
        return createdBooking;
    }

    public static String bytesToHex(byte[] byteArray) {
        String hex = "";
        // Iterating through each byte in the array
        for (byte i : byteArray) {
            hex += String.format("%02X", i);
        }
        return hex;
    }

    public static String getBookingHash(Booking booking) throws NoSuchAlgorithmException {
        final MessageDigest digest = MessageDigest.getInstance("Keccak-256");
        final byte[] encodedhash = digest.digest(
                booking.toString().getBytes(StandardCharsets.UTF_8));
        return bytesToHex(encodedhash);
    }

}
