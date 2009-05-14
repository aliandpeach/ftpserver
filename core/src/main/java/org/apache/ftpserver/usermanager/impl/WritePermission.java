/*
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 *
 *  http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied.  See the License for the
 * specific language governing permissions and limitations
 * under the License.
 */

package org.apache.ftpserver.usermanager.impl;

import org.apache.ftpserver.ftplet.Authority;
import org.apache.ftpserver.ftplet.AuthorizationRequest;

/**
 * <strong>Internal class, do not use directly.</strong>
 * 
 * Class representing a write permission
 *
 * @author The Apache MINA Project (dev@mina.apache.org)
 */
public class WritePermission implements Authority {

    private String permissionRoot;

    /**
     * Construct a write permission for the user home directory (/)
     */
    public WritePermission() {
        this.permissionRoot = "/";
    }

    /**
     * Construct a write permission for a file or directory relative to the user
     * home directory
     * 
     * @param permissionRoot
     *            The file or directory
     */
    public WritePermission(final String permissionRoot) {
        this.permissionRoot = permissionRoot;
    }

    /**
     * @see Authority#authorize(AuthorizationRequest)
     */
    public AuthorizationRequest authorize(final AuthorizationRequest request) {
        if (request instanceof WriteRequest) {
            WriteRequest writeRequest = (WriteRequest) request;

            String requestFile = writeRequest.getFile();

            if (requestFile.startsWith(permissionRoot)) {
                return writeRequest;
            } else {
                return null;
            }
        } else {
            return null;
        }
    }

    /**
     * @see Authority#canAuthorize(AuthorizationRequest)
     */
    public boolean canAuthorize(final AuthorizationRequest request) {
        return request instanceof WriteRequest;
    }

}
