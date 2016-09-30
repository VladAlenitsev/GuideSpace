package com.guidespace.security;

import com.cgi.service.MetricsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.File;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.List;
import java.util.Set;

/**
 * Created by Sebastian on 4.07.2016.
 **/

@Controller
@RequestMapping("/rest")
public class SecurityController {

    @Autowired
    private MetricsService metricsService;

    @Value("${init.method}")
    private String initialMethod;

    @Value("${date.frontEndFormat}")
    private String dateFormat;

    @RequestMapping(value = "/methods", method = {RequestMethod.GET}, produces = "application/json; charset=UTF-8")
    @ResponseBody
    public Set<String> getMethodNames() {
        return metricsService.getMethodNames();
    }

    @RequestMapping(value = "/getFreeSpace", method = RequestMethod.GET)
    @ResponseBody
    public List<Long> getFreeSpace() {
        File root = new File(File.separator);
        Long freeSpace = root.getFreeSpace() / 1024 / 1024 / 1024;
        Long usedSpace = (root.getTotalSpace() / 1024 / 1024 / 1024 - freeSpace);
        return Arrays.asList(freeSpace, usedSpace);
    }


    @RequestMapping(value = "/getRuntimeByInputNameAndTime", method = {RequestMethod.GET})
    @ResponseBody
    public List<List<Object>> getRuntimeByInputNameAndTime(@RequestParam(value = "from") String from, @RequestParam(value = "to") String to, @RequestParam(value = "filter", defaultValue = "") String filter) throws ParseException {
        DateFormat format = new SimpleDateFormat(dateFormat);
        return metricsService.plotPointToCoordinates(
                metricsService.getRuntimeByInputNameAndTime(
                        metricsService.getMetricsByInputFile(
                                metricsService.getStreamByDateRange(
                                        format.parse(from), format.parse(to)).filter(
                                        metricItem -> metricItem.getMethodName().equals(filter) || StringUtils.isEmpty(filter)))));
    }

    @RequestMapping(value = "/getNormTime", method = {RequestMethod.GET})
    @ResponseBody
    public List<List<Object>> getSecond(@RequestParam(value = "from") String from, @RequestParam(value = "to") String to, @RequestParam(value = "filter", defaultValue = "") String filter) throws ParseException {
        DateFormat format = new SimpleDateFormat(dateFormat);
        String emptyFilter = filter;
        if (StringUtils.isEmpty(filter)) {
            filter = initialMethod;
        }
        String finalFilter = filter;
        return metricsService.plotPointToCoordinates(
                metricsService.getNormTime(
                        metricsService.getMetricsByInputFile(
                                metricsService.getStreamByDateRange(
                                        format.parse(from), format.parse(to)).filter(
                                        metricItem -> metricItem.getMethodName().equals(emptyFilter) || StringUtils.isEmpty(emptyFilter))), finalFilter));
    }

    @RequestMapping(value = "/getTimeAndSizeMetrics", method = {RequestMethod.GET})
    @ResponseBody
    public List<List<Object>> getTimeAndSizeMetrics(@RequestParam(value = "from") String from, @RequestParam(value = "to") String to, @RequestParam(value = "filter", defaultValue = "") String filter) throws ParseException {
        String emptyFilter = filter;
        DateFormat format = new SimpleDateFormat(dateFormat);
        if (StringUtils.isEmpty(filter)) {
            filter = initialMethod;
        }
        String finalFilter = filter;
        return metricsService.plotPointToCoordinates(
                metricsService.getTimeAndSizeMetrics(
                        metricsService.getMetricsByInputFile(
                                metricsService.getStreamByDateRange(
                                        format.parse(from), format.parse(to)).filter(
                                        metricItem -> metricItem.getMethodName().equals(emptyFilter) || StringUtils.isEmpty(emptyFilter))), finalFilter));

    }


    @RequestMapping(value = "/getCSRF", method = RequestMethod.GET)
    public ResponseEntity<SecurityController> getCSRF() {
        return new ResponseEntity<>(HttpStatus.OK);
    }


    private static class RequestResponse {
        private String message;

        RequestResponse(String message) {
            this.message = message;
        }

        public String getMessage() {
            return message;
        }

        public void setMessage(String message) {
            this.message = message;
        }
    }
}
