package xlike.util;

import java.io.UnsupportedEncodingException;

public class EncodingConverter {

	 public static String decode(final String s, final String encoding)
		      throws UnsupportedEncodingException
		  {
		    if ("utf-8".equalsIgnoreCase(encoding))
		    {
		      return decodeUTF(s);
		    }
		    // the resulting string will never be greater than the encoded string
		    final byte[] result = new byte[s.length()];
		    final char[] chars = s.toCharArray();
		    int position = 0;

		    for (int i = 0; i < chars.length; i++)
		    {
		      final char ch = chars[i];
		      final int b;
		      switch (ch)
		      {
		        case'%':
		          final char lch = s.charAt(++i);
		          final int hb = (Character.isDigit(lch)
		              ? lch - '0'
		              : 10 + Character.toLowerCase(lch) - 'a') & 0xF;
		          final char hch = s.charAt(++i);
		          final int lb = (Character.isDigit(hch)
		              ? hch - '0'
		              : 10 + Character.toLowerCase(hch) - 'a') & 0xF;
		          b = (hb << 4) | lb;
		          break;
		        case'+':
		          b = ' ';
		          break;
		        default:
		          b = ch;
		      }
		      result[position] = (byte) b;
		      position += 1;
		    }
		    return new String(result, 0, position, encoding);
		  }
	 /**
	   * Decodes the given string using the encoding UTF-8.
	   *
	   * @param s        the string that should be encoded.
	   * @return the encoded string.
	   */
	  public static String decodeUTF(final String s)
	  {
	    final StringBuffer sbuf = new StringBuffer();
	    final char[] chars = s.toCharArray();
	    final int l = chars.length;
	    int sumb = 0;
	    for (int i = 0, more = -1; i < l; i++)
	    {
	      /* Get next byte b from URL segment s */
	      final int ch = chars[i];
	      final int b;
	      switch (ch)
	      {
	        case'%':
	          final char lch = s.charAt(++i);
	          final int hb = (Character.isDigit(lch)
	              ? lch - '0'
	              : 10 + Character.toLowerCase(lch) - 'a') & 0xF;
	          final char hch = s.charAt(++i);
	          final int lb = (Character.isDigit(hch)
	              ? hch - '0'
	              : 10 + Character.toLowerCase(hch) - 'a') & 0xF;
	          b = (hb << 4) | lb;
	          break;
	        case'+':
	          b = ' ';
	          break;
	        default:
	          b = ch;
	      }
	      /* Decode byte b as UTF-8, sumb collects incomplete chars */
	      if ((b & 0xc0) == 0x80)
	      {      // 10xxxxxx (continuation byte)
	        sumb = (sumb << 6) | (b & 0x3f);  // Add 6 bits to sumb
	        if (--more == 0)
	        {
	          sbuf.append((char) sumb); // Add char to sbuf
	        }
	      }
	      else if ((b & 0x80) == 0x00)
	      {    // 0xxxxxxx (yields 7 bits)
	        sbuf.append((char) b);      // Store in sbuf
	      }
	      else if ((b & 0xe0) == 0xc0)
	      {    // 110xxxxx (yields 5 bits)
	        sumb = b & 0x1f;
	        more = 1;        // Expect 1 more byte
	      }
	      else if ((b & 0xf0) == 0xe0)
	      {    // 1110xxxx (yields 4 bits)
	        sumb = b & 0x0f;
	        more = 2;        // Expect 2 more bytes
	      }
	      else if ((b & 0xf8) == 0xf0)
	      {    // 11110xxx (yields 3 bits)
	        sumb = b & 0x07;
	        more = 3;        // Expect 3 more bytes
	      }
	      else if ((b & 0xfc) == 0xf8)
	      {    // 111110xx (yields 2 bits)
	        sumb = b & 0x03;
	        more = 4;        // Expect 4 more bytes
	      }
	      else /*if ((b & 0xfe) == 0xfc)*/
	      {  // 1111110x (yields 1 bit)
	        sumb = b & 0x01;
	        more = 5;        // Expect 5 more bytes
	      }
	      /* We don't test if the UTF-8 encoding is well-formed */
	    }
	    return sbuf.toString();
	  }

}
