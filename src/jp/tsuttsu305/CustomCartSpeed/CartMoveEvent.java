package jp.tsuttsu305.CustomCartSpeed;

import java.util.regex.Pattern;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
import org.bukkit.block.Sign;
import org.bukkit.entity.Minecart;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.vehicle.VehicleMoveEvent;

public class CartMoveEvent implements Listener {

	private CustomCartSpeed ccp = null;

	public CartMoveEvent(CustomCartSpeed ccp) {
		this.ccp = ccp;
		// TODO 自動生成されたコンストラクター・スタブ
	}

@EventHandler
	public void onCartMove(VehicleMoveEvent event){
		//defaultの速度を指定
		double setSpeed = 0.4D;
		//Entity cart = event.getVehicle().getVehicle();
		//EntityがMineCartか判定。
		if (event.getVehicle().getPassenger() instanceof Player){
			//移動先の座標取得
			Location toLoc = event.getTo();
			//移動先がディテクターレールかを判定
			if (toLoc.getBlock().getType() == Material.DETECTOR_RAIL){
				Block toBlock = toLoc.getBlock();
				//ディテクターレールを中心として8方位のブロック情報を取得。ちなみに北を基点として時計回り
				//toBlockが中心
				Block[] searchBlock = {
						toBlock.getRelative(BlockFace.NORTH ),
						toBlock.getRelative(BlockFace.NORTH_EAST),
						toBlock.getRelative(BlockFace.EAST),
						toBlock.getRelative(BlockFace.SOUTH_EAST),
						toBlock.getRelative(BlockFace.SOUTH),
						toBlock.getRelative(BlockFace.SOUTH_WEST),
						toBlock.getRelative(BlockFace.WEST),
						toBlock.getRelative(BlockFace.NORTH_WEST),
						};
				//複数あった場合の判定用
				boolean signFound = false;
				//8方位に看板があるか判定
				for(int i = 0;i<=7;i++){
					if(searchBlock[i].getType() == Material.WALL_SIGN || searchBlock[i].getType() == Material.SIGN_POST){
						/*看板は以下の書式
						 * *****************
						 * 		[Cart Speed]		*　←小文字でもOKにする。
						 * 		0.4			*
						 * 			null				*
						 * 			null				*
						 *******************
						 */
						//看板の内容取得
						String[] signLine = ((Sign) searchBlock[i].getState()).getLines();
						//1と2行めを判定
						if (signLine[0].equalsIgnoreCase("[cart speed]") && Pattern.matches("^\\d+(\\.\\d+)?$", signLine[1])){
							//ダブり判定
							if(signFound){
								//ダブリが判明したら処理強制中断
								setSpeed = 0.4D;
								((Minecart) event.getVehicle()).setMaxSpeed(setSpeed);
							}else{
								//String[] line2 = signLine[1].split(":");
								//line2[1] = line2[1].replaceAll(" ", "");
									setSpeed = Double.parseDouble(signLine[1]);
									signFound = true;
							}
						}
						if (setSpeed <= ccp.confmax){
						((Minecart) event.getVehicle()).setMaxSpeed(setSpeed);
						}else{
							((Minecart) event.getVehicle()).setMaxSpeed(ccp.confmax);
						}
					}
				}
			}
		}
		return;
	}

}
