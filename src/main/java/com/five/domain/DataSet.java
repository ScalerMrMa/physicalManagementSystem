package com.five.domain;

import lombok.Data;

/**
 * @author MrMa
 * @version 1.0
 * @description
 */
@Data
public class DataSet {
   private String[][] source;

   public DataSet(String[][] source) {
      this.source = source;
   }
}
