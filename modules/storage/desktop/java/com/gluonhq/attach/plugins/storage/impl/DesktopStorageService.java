/*
 * Copyright (c) 2016, 2018 Gluon
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.

 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 *
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS" AND
 * ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE IMPLIED
 * WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE ARE
 * DISCLAIMED. IN NO EVENT SHALL GLUON BE LIABLE FOR ANY
 * DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES
 * (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES;
 * LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND
 * ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT
 * (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS
 * SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 */
package com.gluonhq.attach.plugins.storage.impl;


import com.gluonhq.attach.plugins.storage.StorageService;
import java.io.File;
import java.util.Optional;

/**
 * An implementation of StorageService, it currently writes to the users home directory under '.gluon'
 */
public class DesktopStorageService implements StorageService {

    @Override
    public Optional<File> getPrivateStorage() {
        try {
            String home = System.getProperty("user.home");
            File f = new File(home, ".gluon");
            if (!f.isDirectory()) {
                f.mkdirs();
            }
            return Optional.of(f);
        } catch (Exception e) {
            return Optional.empty();
        }
    }

    @Override
    public Optional<File> getPublicStorage(String subdirectory) {
        try {
            String home = System.getProperty("user.home");
            File f;
            if (null == subdirectory) {
                f = new File(home);
            } else {
                f = new File(home, subdirectory);
            }
            return Optional.of(f);
        } catch (Exception e) {
            return Optional.empty();
        }
    }

    @Override
    public boolean isExternalStorageWritable() {
        return true;
    }

    @Override
    public boolean isExternalStorageReadable() {
        return true;
    }
}
