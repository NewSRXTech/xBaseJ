package org.xBaseJ.test;
/**
 * xBaseJ - Java access to dBase files
 *
 * <p>Copyright 1997-2014 - American Coders, LTD - Raleigh NC USA
 *
 * <p>All rights reserved
 *
 * <p>Currently supports only dBase III format DBF, DBT and NDX files
 *
 * <p>dBase IV format DBF, DBT, MDX and NDX files
 *
 * <p>American Coders, Ltd <br>
 * P. O. Box 97462 <br>
 * Raleigh, NC 27615 USA <br>
 * 1-919-846-2014 <br>
 * http://www.americancoders.com
 *
 * @author Joe McVerry, American Coders Ltd.
 * @author Michael Joyner https://github.com/michael-newsrx
 * @author Tyryshkin Alexander https://github.com/TYSDEV @Version 20170109
 *     <p>This library is free software; you can redistribute it and/or modify it under the terms of
 *     the GNU Library Lesser General Public License as published by the Free Software Foundation;
 *     either version 2 of the License, or (at your option) any later version.
 *     <p>This library is distributed in the hope that it will be useful, but WITHOUT ANY WARRANTY;
 *     without even the implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See
 *     the GNU Library General Public License for more details.
 *     <p>You should have received a copy of the GNU Library Lesser General Public License along
 *     with this library; if not, write to the Free Foundation, Inc., 59 Temple Place, Suite 330,
 *     Boston, MA 02111-1307 USA
 */
import java.io.IOException;

import org.xBaseJ.Util;
import org.xBaseJ.xBaseJException;
import org.xBaseJ.fields.CharField;
import org.xBaseJ.fields.DateField;

import junit.framework.TestCase;

public class TestNoBlanks extends TestCase {

  public TestNoBlanks(String arg0) {
    super(arg0);
  }

  @Override
  public void setUp() {
    try {
      super.setUp();
    } catch (Exception e) {

      e.printStackTrace();
    }
    try {
      Util.closexBaseJProperty();
      Util.copyFile("testfiles/noblanks.xBaseJ.txt", "org.xBaseJ.properties");
    } catch (Exception e1) {
      e1.printStackTrace();
      System.exit(0);
    }
  }

  public void testBlank() {

    assertTrue(org.xBaseJ.Util.dontTrimFields() == false);
    /*
     * try { Util.setxBaseJProperty("fieldFilledWithSpaces", "true"); }
     * catch (IOException e1) {
     *
     * e1.printStackTrace(); fail(e1.getMessage()); }
     */
    assertTrue(org.xBaseJ.Util.fieldFilledWithSpaces());

    try {
      CharField f = new CharField("char", 5);
      DateField df = new DateField("date");
      f.put("");
      df.put("");
      assertEquals("     ", f.get());
      assertEquals("        ", df.get());
    } catch (xBaseJException e) {
      e.printStackTrace();
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  @Override
  public void tearDown() {
    try {
      Util.closexBaseJProperty();
      Util.copyFile("testfiles/reset.xBaseJ.txt", "org.xBaseJ.properties");
    } catch (Exception e1) {
      e1.printStackTrace();
      System.exit(0);
    }
  }
}
