package com.ca.cheyi02.myfirstapp;

import android.os.Bundle;

import com.l7tech.msso.MobileSsoConfig;

import java.util.ArrayList;
import java.util.Arrays;

public class Config {

    // URI of our web service that downloads a product list in JSON format
    static final String PRODUCT_LIST_DOWNLOAD_URI;

    // Token server configuration
    static final Bundle ssoConf;

    static {

        //MAG Sdk Configuration
        Bundle hawking = new Bundle();

        hawking.putString(MobileSsoConfig.PROP_TOKEN_HOSTNAME, "caw2015api.casecurecloud.com");
        hawking.putInt(MobileSsoConfig.PROP_TOKEN_PORT_HTTPS, 443);
        hawking.putString(MobileSsoConfig.PROP_TOKEN_URI_PREFIX, "magdev");
        hawking.putString(MobileSsoConfig.PROP_ORGANIZATION, "Android Test LTD");
        hawking.putString(MobileSsoConfig.PROP_CLIENT_ID, "54fc1188-bed2-4e10-b544-873fb031a563");
        hawking.putString(MobileSsoConfig.PROP_OAUTH_SCOPE, "openid");
        hawking.putBoolean(MobileSsoConfig.PROP_SSO_ENABLED, true);
        hawking.putBoolean(MobileSsoConfig.PROP_LOCATION_ENABLED, false);
        hawking.putBoolean(MobileSsoConfig.PROP_MSISDN_ENABLED, false);

        hawking.putBoolean(MobileSsoConfig.PROP_TRUSTED_PUBLIC_PKI, true);

        hawking.putStringArrayList(MobileSsoConfig.PROP_TRUSTED_CERTS_PEM, new ArrayList<String>(Arrays.asList(

                "-----BEGIN CERTIFICATE-----\n" +
                        "MIIFMTCCBBmgAwIBAgIGZtE6fQ4GMA0GCSqGSIb3DQEBCwUAMIG0MQswCQYDVQQGEwJVUzEQMA4G\n" +
                        "A1UECBMHQXJpem9uYTETMBEGA1UEBxMKU2NvdHRzZGFsZTEaMBgGA1UEChMRR29EYWRkeS5jb20s\n" +
                        "IEluYy4xLTArBgNVBAsTJGh0dHA6Ly9jZXJ0cy5nb2RhZGR5LmNvbS9yZXBvc2l0b3J5LzEzMDEG\n" +
                        "A1UEAxMqR28gRGFkZHkgU2VjdXJlIENlcnRpZmljYXRlIEF1dGhvcml0eSAtIEcyMB4XDTE0MTAx\n" +
                        "NDE1MDgwNloXDTE1MTAxNDE1MDgwNlowQTEhMB8GA1UECxMYRG9tYWluIENvbnRyb2wgVmFsaWRh\n" +
                        "dGVkMRwwGgYDVQQDDBMqLmNhc2VjdXJlY2xvdWQuY29tMIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8A\n" +
                        "MIIBCgKCAQEAuU71m2BXzOfV96V5VzxpZhVVcUmYv5LXaFU6UlHw7EC9mqW2/wvuHTIYJhFv02yc\n" +
                        "kH/L2xz4vM1bOijgWArX8V+qAmAlwoIb5eyd+loPWOO40zz13zRSV/JYbhoQxQR6Rzg9KeMZAlHg\n" +
                        "Hghwgz8o49/qRH8quUuD6jyP9rjsqp0uPXPncNVX6S2n/IYP5t0GVB9wFa54UXRzzQyCdYTEtqUt\n" +
                        "1lWlyzhcIbY5CorLieLZKhFe8Mn+RUi9w6233tVKs4WTgRocN+0UBHOZKIY5mbVbQuBoXLxufUEJ\n" +
                        "QIAS6JhAu6NWicKMEyhjjex6onavuhqr/aC92qrs0shbSyIotQIDAQABo4IBuTCCAbUwDAYDVR0T\n" +
                        "AQH/BAIwADAdBgNVHSUEFjAUBggrBgEFBQcDAQYIKwYBBQUHAwIwDgYDVR0PAQH/BAQDAgWgMDYG\n" +
                        "A1UdHwQvMC0wK6ApoCeGJWh0dHA6Ly9jcmwuZ29kYWRkeS5jb20vZ2RpZzJzMS04Ny5jcmwwUwYD\n" +
                        "VR0gBEwwSjBIBgtghkgBhv1tAQcXATA5MDcGCCsGAQUFBwIBFitodHRwOi8vY2VydGlmaWNhdGVz\n" +
                        "LmdvZGFkZHkuY29tL3JlcG9zaXRvcnkvMHYGCCsGAQUFBwEBBGowaDAkBggrBgEFBQcwAYYYaHR0\n" +
                        "cDovL29jc3AuZ29kYWRkeS5jb20vMEAGCCsGAQUFBzAChjRodHRwOi8vY2VydGlmaWNhdGVzLmdv\n" +
                        "ZGFkZHkuY29tL3JlcG9zaXRvcnkvZ2RpZzIuY3J0MB8GA1UdIwQYMBaAFEDCvSeOzDSDMKIz1/ts\n" +
                        "s/C0LIDOMDEGA1UdEQQqMCiCEyouY2FzZWN1cmVjbG91ZC5jb22CEWNhc2VjdXJlY2xvdWQuY29t\n" +
                        "MB0GA1UdDgQWBBRZA/p7QDCRPlVVZqKlOBVzWPWuMzANBgkqhkiG9w0BAQsFAAOCAQEAkJfDbtOv\n" +
                        "btNixSgxgGLdw4Mz0HoA+KpSKRAsLekwODw+/FYXyyUaBSsfd9vEZ253ZEY0An/IRYG4NI1EKIX7\n" +
                        "dGpu3eJ9QGf92W/pPq1EOW4HZ3+hK+kO2cyXTnSIEO8Jdgza7txTHdzjyPAOmDWw5bV/tfx9U2wm\n" +
                        "azgm0XM1tdQxoEB+4+eXB9CW32rFPosRVjol0SIjQDnzBIWvcNT3qg3yAuMKuiSiOT/fJ+u2zXTt\n" +
                        "zxcAHoxtBn68aALdkf0ingUgMDfYSnRyzBnESzmZyO9mt+Agc9KDD0oYxGCJUMswHdT6Ct6OL3HN\n" +
                        "g1PfwkrSo3YbNXBr1rkwzpbXWXa4RQ==\n" +
                        "-----END CERTIFICATE-----"
        )));
        hawking.putString(MobileSsoConfig.PROP_AUTHORIZE_REDIRECT_URI, "https://android.ssosdk.ca.com/android");

        //Set the config
        ssoConf = hawking;

        //Sample endpoint
        PRODUCT_LIST_DOWNLOAD_URI = "https://" + ssoConf.getString(MobileSsoConfig.PROP_TOKEN_HOSTNAME) +
                "/protected/resource/products?operation=listProducts";

    }
}