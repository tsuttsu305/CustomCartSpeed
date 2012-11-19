package jp.tsuttsu305.CustomCartSpeed;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Minecart;
import org.bukkit.event.Listener;
import org.bukkit.event.vehicle.VehicleMoveEvent;

public class CartMoveEvent implements Listener {

	public CartMoveEvent(CustomCartSpeed customCartSpeed) {
		// TODO 自動生成されたコンストラクター・スタブ
	}
	
	public void onCartMove(VehicleMoveEvent event){
		Entity cart = event.getVehicle().getVehicle();
		//EntityがMineCartか判定。
		if (cart instanceof Minecart){
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
					if(searchBlock[i].getType() == Material.SIGN || searchBlock[i].getType() == Material.SIGN_POST){
						
					}
				}
			}
		}
		return;
	}

}
