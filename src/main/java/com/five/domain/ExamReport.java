package com.five.domain;

import lombok.Data;

/**
 * @author MrMa
 * @version 1.0
 * @description
 */
@Data
public class ExamReport {
   private String examReportId;
   private String examRealName;
   private String examItemId;
   private String examItemName;
   private String examDate;
   private String examDoctorContent;
   private String examCreateTime;
}
