package org.kevoree.library.defaultNodeTypes.osgi.deploy.command

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

import org.kevoree.DeployUnit
import org.slf4j.LoggerFactory
import org.kevoree.library.defaultNodeTypes.jcl.deploy.context.KevoreeDeployManager
import org.kevoree.library.defaultNodeTypes.jcl.deploy.command.CommandHelper
import org.kevoree.library.defaultNodeTypes.osgi.deploy.{KevoreeOSGIMapping, OSGIKevoreeDeployManager}
import org.kevoree.api.PrimitiveCommand

/**
 * User: Erwan Daubert - erwan.daubert@gmail.com
 * Date: 08/12/11
 * Time: 20:28
 *
 * @author Erwan Daubert
 * @version 1.0
 */

case class StartThirdPartyCommand (c: DeployUnit, nodeName: String) extends PrimitiveCommand {
  var logger = LoggerFactory.getLogger(this.getClass)

  def execute (): Boolean = {
    KevoreeDeployManager.bundleMapping
      .find(map => map.objClassName == c.getClass.getName && map.name == CommandHelper.buildKEY(c)) match {
      case None => false
      case Some(mapfound) => {
        try {
          OSGIKevoreeDeployManager.getBundleContext.getBundle(mapfound.asInstanceOf[KevoreeOSGIMapping].bundleID).start()
          true
        } catch {
          case _@e => logger.debug("Unable to initialiaze a ThirdParty: {}", c.getName, e); false
        }
      }
    }
  }

  def undo () {


  }
}