/*
 * Copyright (c) 2014, Andreas Fagschlunger. All rights reserved.
 * 
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions
 * are met:
 * 
 *   - Redistributions of source code must retain the above copyright
 *     notice, this list of conditions and the following disclaimer.
 * 
 *   - Redistributions in binary form must reproduce the above copyright
 *     notice, this list of conditions and the following disclaimer in the
 *     documentation and/or other materials provided with the distribution.
 * 
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS
 * IS" AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO,
 * THE IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR
 * PURPOSE ARE DISCLAIMED.  IN NO EVENT SHALL THE COPYRIGHT OWNER OR
 * CONTRIBUTORS BE LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL,
 * EXEMPLARY, OR CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO,
 * PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA, OR
 * PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF
 * LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING
 * NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS
 * SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
*/

package at.o2xfs.win32;

import org.junit.Assert;
import org.junit.Test;

import at.o2xfs.common.Hex;

public class BitConverterTest {

	@Test
	public void int0x0B020003() {
		byte[] expecteds = Hex.decode("0300020B");
		long expected = 0x0B020003;
		byte[] actuals = BitConverter.getBytes(expecteds.length, expected);
		Assert.assertArrayEquals(expecteds, actuals);
		Assert.assertEquals(expected, BitConverter.toLong(actuals));
	}

	@Test
	public final void from0xFFToInt() {
		int expected = 0xFF;
		byte[] src = new byte[] { (byte) expected };
		int actual = BitConverter.toInt(src);
		Assert.assertEquals(expected, actual);
	}

	@Test
	public final void longToBytes() {
		byte[] expecteds = Hex.decode("FFFFFFFFFFFFFF7F");
		byte[] actuals = BitConverter
				.getBytes(expecteds.length, Long.MAX_VALUE);
		Assert.assertArrayEquals(expecteds, actuals);
	}
}