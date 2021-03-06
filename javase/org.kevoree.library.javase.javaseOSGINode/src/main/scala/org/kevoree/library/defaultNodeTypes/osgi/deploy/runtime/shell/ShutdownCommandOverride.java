/**
 * Licensed under the GNU LESSER GENERAL PUBLIC LICENSE, Version 3, 29 June 2007;
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * 	http://www.gnu.org/licenses/lgpl-3.0.txt
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.kevoree.library.defaultNodeTypes.osgi.deploy.runtime.shell;

import org.apache.felix.shell.impl.ShutdownCommandImpl;
import org.osgi.framework.BundleContext;

import java.io.PrintStream;

/**
 * Created by IntelliJ IDEA.
 * User: duke
 * Date: 27/09/11
 * Time: 19:59
 * To change this template use File | Settings | File Templates.
 */
public class ShutdownCommandOverride extends ShutdownCommandImpl {

    public ShutdownCommandOverride(BundleContext context) {
        super(context);
    }
    public void execute(String s, PrintStream out, PrintStream err)
	    {
            new Thread(){
                @Override
                public void run(){
                  Runtime.getRuntime().exit(0);
                }
            }.start();
	    }

}
