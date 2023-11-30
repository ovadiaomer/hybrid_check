package com.chrome.handler

import org.apache.http.client.methods.CloseableHttpResponse
import org.apache.http.client.methods.HttpGet
import org.apache.http.conn.HttpHostConnectException
import org.apache.http.impl.client.CloseableHttpClient
import org.apache.http.impl.client.HttpClients
import org.json.JSONArray
import org.json.JSONObject
import java.io.BufferedReader
import java.io.InputStreamReader
import java.util.*

class ChromedriverHandler {
    private fun chromeDriverHandler() {
        while (true) {
            try {
                val url = "http://127.0.0.1:9515/wd/hub/sessions"
                val response = sendGetRequest(url)
                if (!response.isEmpty() || response != null) {
                    val responseJson = JSONObject(response)
                    val valueJsonArray: JSONArray = responseJson.getJSONArray("value")
                    if (valueJsonArray.length() < 1) {
                        println("******* Handling Chromedriver, Making Responsive. ********** ")
                        if (System.getProperty("os.name").trim { it <= ' ' }.lowercase(Locale.getDefault())
                                .matches("^windows.*".toRegex())
                        ) {
                            Runtime.getRuntime().exec(arrayOf("cmd.exe", "/C", "taskkill /f /im chromedriver.exe"))
                        } else {
                            Runtime.getRuntime().exec("killall chromedriver")
                        }
                    }
                }
            } catch (e: Exception) {
            }
            try {
                Thread.sleep(3000)
            } catch (e: InterruptedException) {
            }
        }
    }

    private fun sendGetRequest(ServerURL: String): String {
        var result = StringBuffer()
        var httpclient: CloseableHttpClient? = null
        var response: CloseableHttpResponse? = null
        try {
            httpclient = HttpClients.createDefault()
            val cleanURL = ServerURL.replace("%%", "").trim()
            val getRequest = HttpGet(cleanURL)
            try {
                response = httpclient.execute(getRequest)
            } catch (h: HttpHostConnectException) {
                // Log the exception or handle it as needed
            }
            response?.use { validResponse ->
                BufferedReader(InputStreamReader(validResponse.entity.content)).use { rd ->
                    var line: String? = rd.readLine()
                    while (line != null) {
                        result.append(line)
                        line = rd.readLine()
                    }
                }
            }
        } catch (e: Exception) {
            // Log the exception or handle it as needed
        } finally {
            try {
                response?.close()
                httpclient?.close()
            } catch (e: Exception) {
                // Log the exception or handle it as needed
            }
        }
        return result.toString()
    }


    companion object {
        fun chromeDriverHandlerThread(): Thread {
            val chromeDriverHandler = Thread { ChromedriverHandler().chromeDriverHandler() }
            println("starting thread to monitor chrome driver ...")
            return chromeDriverHandler
        }
    }
}