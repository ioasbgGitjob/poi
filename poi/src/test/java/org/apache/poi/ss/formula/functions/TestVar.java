
/* ====================================================================
   Licensed to the Apache Software Foundation (ASF) under one or more
   contributor license agreements.  See the NOTICE file distributed with
   this work for additional information regarding copyright ownership.
   The ASF licenses this file to You under the Apache License, Version 2.0
   (the "License"); you may not use this file except in compliance with
   the License.  You may obtain a copy of the License at

       http://www.apache.org/licenses/LICENSE-2.0

   Unless required by applicable law or agreed to in writing, software
   distributed under the License is distributed on an "AS IS" BASIS,
   WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
   See the License for the specific language governing permissions and
   limitations under the License.
==================================================================== */
package org.apache.poi.ss.formula.functions;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFFormulaEvaluator;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.apache.poi.ss.util.Utils.addRow;
import static org.apache.poi.ss.util.Utils.assertDouble;

/**
 * Testcase for functions: VAR.S(), VAR.P()
 */
public class TestVar {

    //https://support.microsoft.com/en-us/office/var-s-function-913633de-136b-449d-813e-65a00b2b990b
    //https://support.microsoft.com/en-us/office/var-p-function-73d1285c-108c-4843-ba5d-a51f90656f3a
    @Test
    void testMicrosoftExample1() throws IOException {
        try (HSSFWorkbook wb = initWorkbook1()) {
            HSSFFormulaEvaluator fe = new HSSFFormulaEvaluator(wb);
            HSSFCell cell = wb.getSheetAt(0).getRow(0).createCell(12);
            assertDouble(fe, cell, "VARP(A3:A12)", 678.84, 0.00000000001);
            assertDouble(fe, cell, "VAR.P(A3:A12)", 678.84, 0.00000000001);
            assertDouble(fe, cell, "VAR(A3:A12)", 754.27, 0.005);
            assertDouble(fe, cell, "VAR.S(A3:A12)", 754.27, 0.005);
        }
    }

    private HSSFWorkbook initWorkbook1() {
        HSSFWorkbook wb = new HSSFWorkbook();
        HSSFSheet sheet = wb.createSheet();
        addRow(sheet, 0, "Data");
        addRow(sheet, 1, "Strength");
        addRow(sheet, 2, 1345);
        addRow(sheet, 3, 1301);
        addRow(sheet, 4, 1368);
        addRow(sheet, 5, 1322);
        addRow(sheet, 6, 1310);
        addRow(sheet, 7, 1370);
        addRow(sheet, 8, 1318);
        addRow(sheet, 9, 1350);
        addRow(sheet, 10, 1303);
        addRow(sheet, 11, 1299);
        return wb;
    }
}
