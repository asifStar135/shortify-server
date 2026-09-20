package com.shortly.Utils;

import jakarta.mail.internet.MimeMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

@Component
public class EmailEngine {
    @Autowired
    private JavaMailSender mailSender;

    @Async
    public void sendEmail(String code, String toEmail) {
        try {
            MimeMessage message = mailSender.createMimeMessage();
            MimeMessageHelper helper =
                    new MimeMessageHelper(message, true, "UTF-8");
            helper.setTo(toEmail);
            helper.setSubject("Shortly password reset code");

            String htmlContent = EMAIL_HTML.replace("${code}", code);
            helper.setText(htmlContent, true);

            mailSender.send(message);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    private final String EMAIL_HTML = """
            <!DOCTYPE html>
            <html>
            <head>
                <meta charset="utf-8">
                <meta name="viewport" content="width=device-width, initial-scale=1.0">
                <title>Reset Your Password</title>
            </head>
            <body style="font-family: -apple-system, BlinkMacSystemFont, 'Segoe UI', Roboto, Helvetica, Arial, sans-serif; background-color: #f4f5f7; margin: 0; padding: 0; -webkit-font-smoothing: antialiased;">
                <table border="0" cellpadding="0" cellspacing="0" width="100%" style="background-color: #f4f5f7; padding: 40px 20px;">
                    <tr>
                        <td align="center">
                            <table border="0" cellpadding="0" cellspacing="0" width="100%" style="max-width: 480px; background-color: #ffffff; border-radius: 12px; box-shadow: 0 4px 12px rgba(0, 0, 0, 0.05); overflow: hidden;">
                                <!-- Header/Accent Bar -->
                                <tr>
                                    <td style="background-color: #4F46E5; height: 6px;"></td>
                                </tr>
                                <!-- Body Content -->
                                <tr>
                                    <td style="padding: 40px 32px; text-align: center;">
                                        <h1 style="color: #1F2937; font-size: 22px; font-weight: 700; margin: 0 0 16px 0; letter-spacing: -0.5px;">Password Reset Request</h1>
                                        <p style="color: #4B5563; font-size: 14px; line-height: 22px; margin: 0 0 24px 0;">We received a request to reset your account password. Use the verification code below to proceed. This code is valid for 10 minutes.</p>
            
                                        <!-- Code Container Box -->
                                        <div style="background-color: #F3F4F6; border: 1px solid #E5E7EB; border-radius: 8px; padding: 16px; margin-bottom: 24px;">
                                            <span style="display: block; color: #9CA3AF; font-size: 11px; font-weight: 600; text-transform: uppercase; letter-spacing: 1px; margin-bottom: 6px;">Your One-Time Code</span>
                                            <span style="font-family: 'Courier New', Courier, monospace; color: #111827; font-size: 32px; font-weight: 700; letter-spacing: 4px; display: inline-block;">${code}</span>
                                        </div>
            
                                        <p style="color: #9CA3AF; font-size: 12px; line-height: 18px; margin: 0;">If you didn't request this change, you can safely ignore this email. Your password will remain secure.</p>
                                    </td>
                                </tr>
                                <!-- Footer -->
                                <tr>
                                    <td style="background-color: #FAFAFA; border-top: 1px solid #F3F4F6; padding: 16px 32px; text-align: center;">
                                        <p style="color: #9CA3AF; font-size: 11px; margin: 0;">&copy; 2026 Shortly. All rights reserved.</p>
                                    </td>
                                </tr>
                            </table>
                        </td>
                    </tr>
                </table>
            </body>
            </html>
            """;
}
