package com.Stranger.Mythology.Items;

import com.Stranger.Mythology.Items.impl.Weapons.Fractured_Holy_Sword;
import com.Stranger.Mythology.Items.impl.Wearables.Jin_Lan_Jia_Sha_Fang;
import com.Stranger.Mythology.Items.impl.Weapons.Peach_Wood_Sword;
import com.Stranger.Mythology.Main;
import com.Stranger.Mythology.Players.CooldownManager;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import javax.annotation.Nullable;
import java.time.Duration;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;

public abstract class Item {
    public Main plugin;
    public static HashMap<String, Item> items=new HashMap<String, Item>();

    protected String identifier;
    protected String name;
    protected List<String> lore;
    protected Material material;
    @Nullable protected String abilityName;
    @Nullable protected List<String> abilityDescription;
    protected String abilityTrigger;
    protected double abilityCooldown;
    protected ItemStack itemStack;
    protected YamlConfiguration config;

    public Item(Main plugin, String identifier) {
        this.plugin = plugin;
        this.config = plugin.getItemConfig();
        this.identifier = identifier;
        this.registerItem(identifier);
    }

    public void registerItem(String identifier) {
        String itemPath = "items." + identifier;
        this.name = config.getString(itemPath + ".name","Error");
        this.lore = config.getStringList(itemPath + ".lore");
        this.abilityName = config.getString(itemPath + ".ability_name");
        this.abilityDescription = config.getStringList(itemPath + ".ability_description");
        this.abilityTrigger = config.getString(itemPath+".ability_trigger","");

        //Cooldown in seconds
        this.abilityCooldown = config.getDouble(itemPath+".cooldown",0);
        System.out.println(this.name+" cooldown: "+this.abilityCooldown);

        this.material = Material.getMaterial(config.getString(itemPath + ".material"));
        System.out.println(this.name+" material: "+this.material.toString());
    }

    public static void AddToListAll(Main plugin) {
        items.put("items.fractured_holy_sword", new Fractured_Holy_Sword(plugin));
        items.put("items.jin_lan_jia_sha_fang",new Jin_Lan_Jia_Sha_Fang(plugin));
        items.put("items.peach_wood_sword",new Peach_Wood_Sword(plugin));
    }

    public void addCooldown(UUID uuid){
        if (abilityCooldown==0){
            System.out.println("Cooldown for "+identifier+" is 0");
            return;
        }
        CooldownManager.setCooldown(uuid,identifier, Duration.ofMillis((long) (1000*abilityCooldown)));
    }

    public void craftItemStack(){
        this.itemStack = new ItemStack(this.material,1);
        ItemMeta meta = this.obtainItemMeta(this.itemStack,this.name, this.abilityName, this.abilityDescription, this.lore);
        this.itemStack.setItemMeta(meta);
        System.out.println(this.name+" ItemStack set");
    }

    public ItemMeta obtainItemMeta(ItemStack item, String name, String ability_name, List<String> ability_description, List<String> the_lore) {
        ItemMeta meta = item.getItemMeta();
        List<String> description=new ArrayList<>();
        meta.setItemName(name);
        meta.setUnbreakable(true);
//        class addElements{
//            static
//        }
        if (ability_name!=null) {
            description.add(ChatColor.translateAlternateColorCodes('&', "&eITEM ABILITY:&r&f "+ability_name+"  &e&l"+this.abilityTrigger));
        }
        description=addToDescription(description,ability_description);
        if (!description.isEmpty()) {
            description.add("");
        }
        description=addToDescription(description,lore);
        meta.setLore(description);
        return meta;
    }

    private List<String> addToDescription(List<String> description,List<String>element){
        if (element!=null && !element.isEmpty()){
            element.replaceAll(text->ChatColor.translateAlternateColorCodes('&',text));
            description.addAll(element);
        }
        return description;
    }

    public void activateAbility(PlayerInteractEvent e) {};
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

    public void changeItemStack(ItemStack item){
        this.itemStack=item;
    }

    public ItemStack getItemStack() {
        return itemStack;
    }
}