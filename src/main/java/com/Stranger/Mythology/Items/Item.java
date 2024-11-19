package com.Stranger.Mythology.Items;

import com.Stranger.Mythology.Items.impl.Fractured_Holy_Sword;
import com.Stranger.Mythology.Items.impl.Jin_Lan_Jia_Sha_Fang;
import com.Stranger.Mythology.Items.impl.Peach_Wood_Sword;
import com.Stranger.Mythology.Main;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import javax.annotation.Nullable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Item {
    public Main plugin;
    public static HashMap<String, Item> items=new HashMap<String, Item>();

    private String identifier;
    private String name;
    private List<String> lore;
    private Material material;
    @Nullable private String abilityName;
    @Nullable private List<String> abilityDescription;
    @Nullable private String abilityTrigger;
    private ItemStack itemStack;

//    private ItemStack itemStack;
//    private ItemMeta itemMeta;

    public Item(Main plugin, String identifier) {
        this.plugin = plugin;
        this.identifier = identifier;
        this.registerItem(identifier);
        //this.setItemStack();
    }

    public void registerItem(String identifier) {
        String itemPath = "items." + identifier;
        this.name = this.plugin.getItemConfig().getString(itemPath + ".name");
        this.lore = this.plugin.getItemConfig().getStringList(itemPath + ".lore");
        this.abilityName = this.plugin.getItemConfig().getString(itemPath + ".ability_name");
        this.abilityDescription = this.plugin.getItemConfig().getStringList(itemPath + ".ability_description");
        this.abilityTrigger=this.plugin.getItemConfig().getString(itemPath+".ability_trigger");

        this.material = Material.getMaterial(this.plugin.getItemConfig().getString(itemPath + ".material"));
        System.out.println(this.name+" material: "+this.material.toString());

        this.itemStack = new ItemStack(this.material,1);
        ItemMeta meta = this.obtainItemMeta(this.itemStack,this.name, this.abilityName, this.abilityDescription, this.lore);
        this.itemStack.setItemMeta(meta);
        System.out.println(this.name+" ItemStack set");
    }

    public static void AddToListAll(Main plugin) {
//        System.out.println("List adding");
//        System.out.println("Holy Sword key: "+plugin.getItemConfig().getString("items.fractured_holy_sword.name"));
//        System.out.println("Jiasha key: "+plugin.getItemConfig().getString("items.jin_lan_jia_sha_fang.name"));
        items.put(plugin.getItemConfig().getString("items.fractured_holy_sword.name"), new Fractured_Holy_Sword(plugin));
        items.put(plugin.getItemConfig().getString("items.jin_lan_jia_sha_fang.name"),new Jin_Lan_Jia_Sha_Fang(plugin));
        items.put(plugin.getItemConfig().getString("items.peach_wood_sword.name"),new Peach_Wood_Sword(plugin));

    }

    public ItemMeta obtainItemMeta(ItemStack item, String name, String ability_name, List<String> ability_description, List<String> the_lore) {
        ItemMeta meta = item.getItemMeta();
        List<String> description=new ArrayList<>();
        meta.setItemName(name);
        meta.setUnbreakable(true);
        class addElements{
            static List<String> addToDescription(List<String> description,List<String>element){
                if (element!=null && !element.isEmpty()){
                    element.replaceAll(text->ChatColor.translateAlternateColorCodes('&',text));
                    description.addAll(element);
                }
                return description;
            }
        }
        if (ability_name!=null) {
            description.add(ChatColor.translateAlternateColorCodes('&', "&eITEM ABILITY:&r&f "+ability_name+"  &e&l"+this.abilityTrigger));
        }
        description=addElements.addToDescription(description,ability_description);
        if (!description.isEmpty()) {
            description.add("");
            //description.add("");
        }
        description=addElements.addToDescription(description,lore);
        meta.setLore(description);
        return meta;
    }

    public void activateAbility(Event e) {};
    public void activateAbility(Player player){}

    public String getIdentifier() {
        return identifier;
    }

    public void setIdentifier(String identifier) {
        this.identifier = identifier;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<String> getDescription() {
        return lore;
    }

    public void setDescription(List<String> description) {
        this.lore = description;
    }

    @Nullable
    public String getAbilityName() {
        return abilityName;
    }

    public void setAbilityName(@Nullable String abilityName) {
        this.abilityName = abilityName;
    }

    @Nullable
    public List<String> getAbilityDescription() {
        return this.abilityDescription;
    }

    public void setAbilityDescription(@Nullable List<String> abilityDescription) {
        this.abilityDescription = abilityDescription;
    }

    public ItemStack getItemStack(){
        return this.itemStack;
    }

    public void changeItemStack(ItemStack item){
        this.itemStack=item;
    }
}