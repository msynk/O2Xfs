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

package at.o2xfs.xfs.service.idc.cmd;

import java.util.concurrent.Callable;

import at.o2xfs.xfs.WFSResult;
import at.o2xfs.xfs.XfsException;
import at.o2xfs.xfs.idc.IDCInfoCommand;
import at.o2xfs.xfs.idc.WfsIDCStatus;
import at.o2xfs.xfs.service.XfsServiceManager;
import at.o2xfs.xfs.service.cmd.XfsInfoCommand;
import at.o2xfs.xfs.service.idc.IDCService;

/**
 * This command reports the full range of information available, including the
 * information that is provided either by the Service Provider or, if present,
 * by any of the security modules. In addition to that, the number of cards
 * retained is transmitted for motor driven card reader/writer (for devices of
 * the other categories this number is always set to zero).
 * 
 * @author Andreas Fagschlunger
 * 
 */
public class IDCStatusCommand implements Callable<WfsIDCStatus> {

	private IDCService idcService = null;

	public IDCStatusCommand(final IDCService idcService) {
		this.idcService = idcService;
	}

	@Override
	public WfsIDCStatus call() throws XfsException {
		XfsInfoCommand infoCommand = new XfsInfoCommand(idcService,
				IDCInfoCommand.STATUS);
		WFSResult wfsResult = infoCommand.call();
		try {
			final WfsIDCStatus idcStatus = new WfsIDCStatus(
					idcService.getXfsVersion(), wfsResult.getResults());
			return new WfsIDCStatus(idcService.getXfsVersion(), idcStatus);
		} finally {
			XfsServiceManager.getInstance().free(wfsResult);
		}
	}
}