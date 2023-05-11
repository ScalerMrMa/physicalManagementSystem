package com.five.domain;

import lombok.Data;

/**
 * @author MrMa
 * @version 1.0
 * @description
 */
@Data
public class Notion {
   private String notionId;
   private String notionTitle;
   private String notionContent;
   private String notionCreateTime;
   private String notionPublisher;
   private String notionEditor;
   private String notionEditTime;
   private String notionStatus;
}
