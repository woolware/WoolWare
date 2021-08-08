

package wool.ware.client.module.impl.movement;

import java.awt.Color;

import org.apache.commons.lang3.StringUtils;
import org.lwjgl.input.Keyboard;

import net.minecraft.client.Minecraft;
import net.minecraft.network.play.client.C03PacketPlayer;
import net.minecraft.potion.Potion;
import wool.ware.client.event.bus.Handler;
import wool.ware.client.event.impl.player.EventCollideUnderPlayer;
import wool.ware.client.event.impl.player.MotionEvent;
import wool.ware.client.event.impl.player.UpdateEvent;
import wool.ware.client.module.Module;
import wool.ware.client.utils.MathUtils;
import wool.ware.client.utils.MoveUtil;
import wool.ware.client.utils.TimerUtil;
import wool.ware.client.utils.value.impl.BooleanValue;
import wool.ware.client.utils.value.impl.EnumValue;
import wool.ware.client.utils.value.impl.NumberValue;

public class Flight extends Module {
    private TimerUtil timer = new TimerUtil();
    private EnumValue<Modes> mode = new EnumValue<>("Mode", Modes.VANILLA);
    private EnumValue<BoostModes> boostModes = new EnumValue<>("Boost Mode", BoostModes.NORMAL);
    private BooleanValue viewbob = new BooleanValue("ViewBob", false);
    private BooleanValue boost = new BooleanValue("Boost", false);
    private BooleanValue extra = new BooleanValue("Extra Boost", false, boost, "true");
    private NumberValue<Float> flyspeed = new NumberValue<>("Fly Speed", 2.0f, 0.1f, 3.0f, 0.1f);
    private double moveSpeed, lastDist, ascension;
    private int level, wait, xd, xdnewtest;

    public Flight() {
        super("Flight", Category.MOVEMENT, new Color(33, 120, 255, 255).getRGB());
        setDescription("Zoom around like an epic gamer.");
    }

    public enum Modes {
        HYPIXEL, VANILLA, CUBECRAFT, ANTIVIRUS, EXPERIMENTAL, SPARTAN, GUARDIAN, VELTPVP, VIPERMC, AAC3, AAC, MCCENTRAL, CRYSTALAC, AREA51, NCP, HYPIXELBOOST, PIKA, CLIENT
    }

    public enum BoostModes {
        NORMAL, DAMAGE, WOWOMG
    }

    
    //DOUBLES
    private double moveSpeed1, lastDistance;
    
    
  
    // INTS
    private double aad;
    double roofY = -1.0D;
    
    //BOOLENS
    private boolean aac;
    boolean wasOnGround = true;
    private boolean failedStart = false;
   

    
    //INTS
    private int boostHypixelState = 1;
    private int state;
    private int delay = 0;
    

    
    @Override
    public void onDisable() {
        if (getMc().thePlayer == null || getMc().theWorld == null) return;
        getMc().timer.timerSpeed = 1f;
        getMc().thePlayer.motionX = getMc().thePlayer.motionZ = 0;
        moveSpeed = getBaseMoveSpeed();
        lastDist = 0.0D;
        xdnewtest = 0;
        ascension = 0;
        xd = 0;
    }

    @Handler
    public void onCollidegay(EventCollideUnderPlayer event) {
     //   event.getList().add(new AxisAlignedBB(getMc().thePlayer.posX, (int) getMc().thePlayer.posY, getMc().thePlayer.posZ));
    }

    @Handler
    public void onUpdate(UpdateEvent event) {
        if (getMc().thePlayer == null) return;
        setSuffix(StringUtils.capitalize(mode.getValue().name().toLowerCase()));
        if (!event.isPre()) {
            double xDist = getMc().thePlayer.posX - getMc().thePlayer.prevPosX;
            double zDist = getMc().thePlayer.posZ - getMc().thePlayer.prevPosZ;
            lastDist = Math.sqrt((xDist * xDist) + (zDist * zDist));
        }
        if (event.isPre()) {
            if (viewbob.getValue() && getMc().thePlayer.isMoving())
                getMc().thePlayer.cameraYaw = 0.089f;
            switch (mode.getValue()) {
                case CUBECRAFT:
                    if (event.isPre()) {
                        getMc().timer.timerSpeed = 0.8f;
                        wait++;
                        float y = (float) Math.floor(getMc().thePlayer.posY);
                        if (wait == 1 && !getMc().thePlayer.onGround) {
                            toggle();
                        }
                        if (event.isPre()) {
                            getMc().thePlayer.motionY = 0;
                            if (wait < 10) {
                                event.setY(y - 1);
                                getMc().thePlayer.motionY = -0.1;
                            } else if (wait > 12) {
                                level++;
                                if (level == 1) {
                                    event.setY(y + 0.72 + MathUtils.getRandomInRange(0.03, 0.05));
                                }
                                if (level == 2) {
                                    event.setY(y + 0.48 + MathUtils.getRandomInRange(0.03, 0.05));
                                }
                                if (level == 3) {
                                    event.setY(y + 0.24 + MathUtils.getRandomInRange(0.03, 0.05));
                                }
                                if (level == 4) {
                                    event.setY(y + MathUtils.getRandomInRange(0.03, 0.05));
                                }
                            }
                        }
                    }
                    break;
                case HYPIXEL:
                    if (event.isPre()) {
                        if (getMc().gameSettings.keyBindJump.isKeyDown()) {
                            getMc().thePlayer.setPosition(getMc().thePlayer.posX, getMc().thePlayer.posY + 0.15, getMc().thePlayer.posZ);
                            getMc().thePlayer.motionY = 0.15;
                        } else if (getMc().gameSettings.keyBindSneak.isKeyDown()) {
                            getMc().thePlayer.setPosition(getMc().thePlayer.posX, getMc().thePlayer.posY - 0.15, getMc().thePlayer.posZ);
                            getMc().thePlayer.motionY = -0.15;
                        } else getMc().thePlayer.motionY = 0;
                        if (getMc().getCurrentServerData() != null && getMc().getCurrentServerData().serverIP != null && getMc().getCurrentServerData().serverIP.toLowerCase().contains("hypixel")) {
                            event.setOnGround(true);
                        }
                        double result = 0.00000000334947 + MathUtils.getRandomInRange(0.00000000014947, 0.00000000064947);
                        if (getMc().thePlayer.ticksExisted % 3 == 0) {
                            event.setY(getMc().thePlayer.posY + result);
                            event.setOnGround(false);
                        }
                        /*if (getMc().thePlayer.ticksExisted % 3 == 0) {
                            ascension += value;
                        }
                        if (ascension > value * 7) {
                            event.setOnGround(false);
                            event.setY(getMc().thePlayer.posY + ascension);
                            ascension /= 1.125F;
                        }*/
                       // Printer.print(""+event.getY());
                        if ((getMc().thePlayer.moveForward != 0.0F || getMc().thePlayer.moveStrafing != 0.0F) && getMc().thePlayer.onGround) {
                            if (!boost.isEnabled()) {
                                final float motionY = 0.42f + (getMc().thePlayer.isPotionActive(Potion.jump) ? ((getMc().thePlayer.getActivePotionEffect(Potion.jump).getAmplifier() + 1) * 0.1F) : 0);
                                //getMc().thePlayer.motionY = motionY;
                             //   getMc().thePlayer.setPosition(getMc().thePlayer.posX, getMc().thePlayer.posY + motionY, getMc().thePlayer.posZ);
                            }
                            if ((boostModes.getValue() == BoostModes.DAMAGE || boostModes.getValue() == BoostModes.WOWOMG) && boost.isEnabled())
                                if (xd == 0) {
                                    getMc().thePlayer.damagePlayer();
                                    xd = 1;
                                }
                        }
                    }
                    break;
                    
                case PIKA:
                	if(mc.thePlayer.onGround) {
                		
                		mc.thePlayer.motionY = 2;
                		if(mc.thePlayer.isAirBorne) {
                			
                			mc.thePlayer.motionY = 2;
                			
                		}
                		
                	}
                	
                	
                case CLIENT:
                	if(mc.thePlayer.onGround) {
                		
                		mc.thePlayer.motionY = 5;

                		if(mc.thePlayer.isAirBorne) { 
                			
                			mc.thePlayer.motionY = 2;

                			
                				if(mc.thePlayer.isDead) {
                					toggle();
                				}
                		}
                		
                	}
                    
                    
                case VANILLA:
                    if (getMc().gameSettings.keyBindJump.isKeyDown())
                        getMc().thePlayer.motionY = flyspeed.getValue() / 2;
                    else if (getMc().gameSettings.keyBindSneak.isKeyDown())
                        getMc().thePlayer.motionY = -flyspeed.getValue() / 2;
                    else getMc().thePlayer.motionY = 0;
                    break;
                    
                case ANTIVIRUS:
                    if (getMc().thePlayer.fallDistance > 0.25 && !getMc().gameSettings.keyBindSneak.isKeyDown()) {
                        getMc().thePlayer.setPosition(getMc().thePlayer.posX, getMc().thePlayer.posY + getMc().thePlayer.fallDistance, getMc().thePlayer.posZ);
                        getMc().thePlayer.fallDistance = 0;
                    }
                    event.setOnGround(true);
                    getMc().thePlayer.motionY = -0.05;

                    if (getMc().gameSettings.keyBindJump.isKeyDown() && getMc().thePlayer.ticksExisted % 2 == 0) {
                        getMc().thePlayer.fallDistance = 2;
                    } else if (getMc().gameSettings.keyBindSneak.isKeyDown() && getMc().thePlayer.fallDistance > 0) {
                        getMc().thePlayer.motionY = -1;
                        getMc().thePlayer.fallDistance = 0;
                        //getMc().thePlayer.fallDistance = 1;
                        // getMc().thePlayer.setPosition(getMc().thePlayer.posX, getMc().thePlayer.posY - 2, getMc().thePlayer.posZ);
                    }
                    
                case SPARTAN:
                     	this.mc.thePlayer.motionY = 0;
                 		this.state += 1;
                 		switch (this.state) {
                 		case 1:
                 			mc.timer.timerSpeed = 1.4F;
                 	    	mc.thePlayer.motionY = 0.0;
                 	    	mc.thePlayer.onGround = true;
                 			break;
                 		case 2:
                 			mc.timer.timerSpeed = 1.5F;
                 	    	mc.thePlayer.motionY = 0.0;
                 	    	mc.thePlayer.onGround = true;
                 			break;
                 		case 3:
                 			mc.timer.timerSpeed = 1.3F;
                 	    	mc.thePlayer.motionY = 0.0;
                 	    	mc.thePlayer.onGround = true;
                 			this.state = 0;
                 			break;
                     	}
                 		
                     
                	
                	
                
            		
            		
                	
               
                	
                case GUARDIAN:
                  	if (mc.gameSettings.keyBindJump.getIsKeyPressed()) {
                		mc.thePlayer.motionX = 0;
                		mc.thePlayer.motionY += 0.5;
                		mc.thePlayer.motionZ = 0;
                	}
                	if (mc.gameSettings.keyBindSneak.getIsKeyPressed()) {
                		mc.thePlayer.motionX = 0;
                		mc.thePlayer.motionY = -0.2;
                		mc.thePlayer.motionZ = 0;
                	}
                    if (mc.thePlayer.isMoving() && !mc.gameSettings.keyBindJump.getIsKeyPressed() && !mc.gameSettings.keyBindSneak.getIsKeyPressed()) {
                        if (mc.thePlayer.motionY > -0.42 && !mc.thePlayer.onGround) {
                        	mc.thePlayer.motionX = 0;
                        	mc.thePlayer.motionZ = 0;
                            return;
                        }
                        for (int i = 0; i < 10; ++i) {
                            mc.thePlayer.sendQueue.addToSendQueue(new C03PacketPlayer.C04PacketPlayerPosition(mc.thePlayer.posX, mc.thePlayer.posY + 1.0E-10, mc.thePlayer.posZ, true));
                        }
                        mc.thePlayer.motionY = 0.44;
                        mc.thePlayer.setSpeed(6F);
                    }
                	
                case VELTPVP:
                	if (mc.gameSettings.keyBindJump.getIsKeyPressed()) {
                		mc.thePlayer.motionX = 0.5;
                		mc.thePlayer.motionY += 1;
                		mc.thePlayer.motionZ = 0.5;
                	}
                	if (mc.gameSettings.keyBindSneak.getIsKeyPressed()) {
                		mc.thePlayer.motionX = 1;
                		mc.thePlayer.motionY = 0.6;
                		mc.thePlayer.motionZ = 1;
                	}
                    if (mc.thePlayer.isMoving() && !mc.gameSettings.keyBindJump.getIsKeyPressed() && !mc.gameSettings.keyBindSneak.getIsKeyPressed()) {
                        if (mc.thePlayer.motionY > -0.42 && !mc.thePlayer.onGround) {
                        	mc.thePlayer.motionX = 0;
                        	mc.thePlayer.motionZ = 0;
                            return;
                        }
                        for (int i = 0; i < 10; ++i) {
                            mc.thePlayer.sendQueue.addToSendQueue(new C03PacketPlayer.C04PacketPlayerPosition(mc.thePlayer.posX, mc.thePlayer.posY + 1.0E-10, mc.thePlayer.posZ, true));
                        }
                        mc.thePlayer.motionY = 0.66;
                        mc.thePlayer.setSpeed(7F);
                    }
                	
                case VIPERMC:

                	if(mc.thePlayer.isMoving()) {
                		mc.thePlayer.setSpeed(4F);
                		if(mc.gameSettings.keyBindJump.isKeyDown()) {
                			mc.thePlayer.motionY = 0.5F;
                		} else if(mc.gameSettings.keyBindSneak.isKeyDown()) {
                			mc.thePlayer.motionY = -0.5F;
                		}
                		
                		if(!mc.gameSettings.keyBindSneak.isKeyDown() && !mc.gameSettings.keyBindJump.isKeyDown()) {
                			mc.thePlayer.motionY = 0;
                		}
                		
                	} else {
                    	mc.thePlayer.setVelocity(0, 0, 0);
                	}
                	
                case AAC3:

                    if(this.delay == 0) {
                       this.mc.timer.timerSpeed = 1.1F;
                    }

                    if(this.delay == 2) {
                       this.mc.thePlayer.motionX *= 1.1D;
                       this.mc.thePlayer.motionZ *= 1.1D;
                       this.mc.thePlayer.motionY = 0.1D;
                    } else if(this.delay > 2) {
                       this.mc.timer.timerSpeed = 1.0F;
                       this.delay = 0;
                    }

                    ++this.delay;
                
                    
                case AAC:
                	mc.thePlayer.setSprinting(false);
        			if ((mc.thePlayer.fallDistance >= 4.0F) && (!this.aac)) {
        				this.aac = true;
        				this.aad = (mc.thePlayer.posY + 3.0D);
        				mc.thePlayer.sendQueue.addToSendQueue(new C03PacketPlayer.C04PacketPlayerPosition(mc.thePlayer.posX, mc.thePlayer.posY, mc.thePlayer.posZ, true));
        			}
        			mc.thePlayer.capabilities.isFlying = false;
        			if (this.aac) {
        				if (mc.thePlayer.onGround) {
        					this.aac = false;
        				}
        				if (mc.thePlayer.posY < this.aad) {
        					mc.thePlayer.sendQueue.addToSendQueue(new C03PacketPlayer.C04PacketPlayerPosition(mc.thePlayer.posX, mc.thePlayer.posY, mc.thePlayer.posZ, true));
        					if (mc.gameSettings.keyBindSneak.pressed) {
        						this.aad -= 2.0D;
        					} else if ((mc.gameSettings.keyBindSneak.pressed) && (mc.thePlayer.posY < this.aad + 0.8D)) {
        						this.aad += 2.0D;
        					} else {
        						mc.thePlayer.motionY = 0.7D;

        						gija(0.8f);
        					}
        				}
        			} else {
        				mc.thePlayer.capabilities.isFlying = false;
        			}
        		
                case MCCENTRAL:
                 	if(mc.thePlayer.onGround) {
                		mc.timer.timerSpeed = 1F;
                	}else {
                		mc.thePlayer.motionY = -0.001;
                		mc.timer.timerSpeed = 0.8F;
                		
                	
                		}
                	
                 	
                 	
                 	
                case HYPIXELBOOST:
                         if(mc.thePlayer.onGround) {
                         	return;
                         }
                         
                         for (int i = 0; i < 10; i++) //Imagine flagging to NCP.
                             mc.getNetHandler().addToSendQueue(new C03PacketPlayer.C04PacketPlayerPosition(mc.thePlayer.posX, mc.thePlayer.posY, mc.thePlayer.posZ, true));
                         
                         double fallDistance = 3.0125; //add 0.0125 to ensure we get the fall dmg
                         while (fallDistance > 0) {
                             mc.getNetHandler().addToSendQueue(new C03PacketPlayer.C04PacketPlayerPosition(mc.thePlayer.posX, mc.thePlayer.posY + 0.0624986421, mc.thePlayer.posZ, false));
                             mc.getNetHandler().addToSendQueue(new C03PacketPlayer.C04PacketPlayerPosition(mc.thePlayer.posX, mc.thePlayer.posY + 0.0625, mc.thePlayer.posZ, false));
                             mc.getNetHandler().addToSendQueue(new C03PacketPlayer.C04PacketPlayerPosition(mc.thePlayer.posX, mc.thePlayer.posY + 0.0624986421, mc.thePlayer.posZ, false));
                             mc.getNetHandler().addToSendQueue(new C03PacketPlayer.C04PacketPlayerPosition(mc.thePlayer.posX, mc.thePlayer.posY + 0.0000013579, mc.thePlayer.posZ, false));
                             fallDistance -= 0.0624986421;
                         }
                         mc.getNetHandler().addToSendQueue(new C03PacketPlayer.C04PacketPlayerPosition(mc.thePlayer.posX, mc.thePlayer.posY, mc.thePlayer.posZ, true));
                         
                         mc.thePlayer.jump();
                         boostHypixelState = 1;
                         moveSpeed = 0.1D;
                         lastDistance = 0D;
                         failedStart = false;
                         
                 	
                 	
                 	
                case CRYSTALAC:
                if (getMc().gameSettings.keyBindJump.isKeyDown()) {
                    getMc().thePlayer.setPosition(getMc().thePlayer.posX, getMc().thePlayer.posY + 0.15, getMc().thePlayer.posZ);
                    getMc().thePlayer.motionY = 0.15;
                } else if (getMc().gameSettings.keyBindSneak.isKeyDown()) {
                    getMc().thePlayer.setPosition(getMc().thePlayer.posX, getMc().thePlayer.posY - 0.15, getMc().thePlayer.posZ);
                    getMc().thePlayer.motionY = -0.15;
                } else getMc().thePlayer.motionY = 0;
                if (getMc().getCurrentServerData() != null && getMc().getCurrentServerData().serverIP != null && getMc().getCurrentServerData().serverIP.toLowerCase().contains("hypixel")) {
                    event.setOnGround(true);
                }
                double result = 0.00000000334947 + MathUtils.getRandomInRange(0.00000000014947, 0.00000000064947);
                if (getMc().thePlayer.ticksExisted % 3 == 0) {
                    event.setY(getMc().thePlayer.posY + result);
                    event.setOnGround(false);
                }
                /*if (getMc().thePlayer.ticksExisted % 3 == 0) {
                    ascension += value;
                }
                if (ascension > value * 7) {
                    event.setOnGround(false);
                    event.setY(getMc().thePlayer.posY + ascension);
                    ascension /= 1.125F;
                }*/
               // Printer.print(""+event.getY());
        
                    
                case AREA51:
                mc.timer.timerSpeed = 1.0F / 3;
    			if (mc.thePlayer.isMoving()) {
    	        	mc.thePlayer.onGround = true;
    	        	mc.thePlayer.isAirBorne = false;
    	        	mc.thePlayer.setSpeed(5F);
    	        	mc.thePlayer.cameraYaw = 0.09F;
    	        	if (!mc.gameSettings.keyBindJump.getIsKeyPressed() && !mc.gameSettings.keyBindSneak.getIsKeyPressed()) {
    	        		mc.thePlayer.motionY = 0F;
    	        		}
    	        	}
    	        	if (mc.gameSettings.keyBindJump.getIsKeyPressed()) {
    	        		mc.thePlayer.motionY += 0.75F;
    	        	}   
    	        	if (mc.gameSettings.keyBindSneak.getIsKeyPressed()) {
    	        		mc.thePlayer.motionY -= 0.75F;
    	        	}   
    	        	if (!mc.thePlayer.isMoving()) {
    	        		mc.thePlayer.motionX = 0;
    	        		mc.thePlayer.motionZ = 0;
    	        	}
    	        	
                case NCP:
                	if (!Minecraft.getMinecraft().thePlayer.onGround && this.wasOnGround) {
                        this.roofY = Minecraft.getMinecraft().thePlayer.posY + 0.0D;
                     }

                     if (Minecraft.getMinecraft().thePlayer.onGround) {
                        this.roofY = -1.0D;
                     }

                     this.wasOnGround = Minecraft.getMinecraft().thePlayer.onGround;
                     if (this.isToggled()) {
                        if (!Minecraft.getMinecraft().thePlayer.isOnLadder() && !Minecraft.getMinecraft().thePlayer.onGround) {
                           if (Keyboard.isKeyDown(57)) {
                              if (this.roofY != -1.0D) {
                                 if (Minecraft.getMinecraft().thePlayer.posY + 0.4D > this.roofY) {
                                    Minecraft.getMinecraft().thePlayer.motionY = 0.0D;
                                 } else {
                                    Minecraft.getMinecraft().thePlayer.motionY = 0.4D;
                                 }
                              }
                           } else if (Keyboard.isKeyDown(42)) {
                              Minecraft.getMinecraft().thePlayer.motionY = -0.4D;
                           } else {
                              Minecraft.getMinecraft().thePlayer.motionY = 0.0D;
                              mc.thePlayer.onGround = true;
                           }
                        }

                        if (this.roofY != -1.0D && Minecraft.getMinecraft().thePlayer.posY > this.roofY) {
                           Minecraft.getMinecraft().thePlayer.setPosition(Minecraft.getMinecraft().thePlayer.posX, this.roofY, Minecraft.getMinecraft().thePlayer.posZ);
                        }
                     }
                
        
    
                
                    break;
                case EXPERIMENTAL:
                    if (event.isPre()) {
                       // event.setOnGround(false);
                        //getMc().thePlayer.motionY = 0;
                        getMc().timer.timerSpeed = 1f;
                        if (getMc().thePlayer.onGround) {
                            if (getMc().thePlayer.ticksExisted % 11 == 0) {
                                double[] jumpValue = new double[]{0.0, 0.42f, 0.7531999805212, 1.00133597911214, 1.16610926093821, 1.12160004615784, 0.96636804164123, 0.73584067272827, 0.43152384527073, 0.05489334703208, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0};
                                for (int i = 0; i < 3; ++i) {
                                    for (int length = jumpValue.length, j = 0; j < length; ++j) {
                                        getMc().getNetHandler().getNetworkManager().sendPacket(new C03PacketPlayer.C04PacketPlayerPosition(getMc().thePlayer.posX, getMc().thePlayer.posY + jumpValue[j], getMc().thePlayer.posZ, false));
                                    }
                                }
                            }
                            if (getMc().thePlayer.ticksExisted % 10 == 0) {
                                getMc().timer.timerSpeed = 0.35f;
                            }
                        }
                        if (getMc().thePlayer.hurtResistantTime == 19 && getMc().thePlayer.onGround) {
                            MoveUtil.setSpeed(5);
                            getMc().thePlayer.motionY = 3;
                            //moveSpeed = 8;
                        }
                       /* getMc().thePlayer.motionY = 0;
                        event.setOnGround(true);
                        getMc().thePlayer.setPosition(getMc().thePlayer.posX, (Math.round(getMc().thePlayer.posY / 0.015625) * 0.015625), getMc().thePlayer.posZ);
                        if (getMc().gameSettings.keyBindJump.isKeyDown()) {
                            getMc().thePlayer.motionY = getMc().gameSettings.keyBindJump.isKeyDown() ? 0.5f : 0f;
                        }*/
                    }

                    break;
                default:
                    break;
            }
        }
    }

    
    
    @Handler
    public void onMotion(MotionEvent event) {
        switch (mode.getValue()) {
            case EXPERIMENTAL:
                //setMoveSpeed(event, 0.24);
                //setMoveSpeed(event, 2);
                break;
            case ANTIVIRUS:
                if (!getMc().thePlayer.onGround) {
                    setMoveSpeed(event, 0.75);
                }
                break;
            case CUBECRAFT:
                getMc().timer.timerSpeed = 1f;
                setMoveSpeed(event, 0);
                if (wait > 12) {
                    if (level == 4) {
                        setMoveSpeed(event, 0.953532);
                        level = 0;
                    }
                    else {
                        setMoveSpeed(event, 0.121984218421847);
                    }
                }
                else {
                    setMoveSpeed(event, 0);
                }
                break;
            case VANILLA:
                setMoveSpeed(event, flyspeed.getValue());
                break;
            case HYPIXEL:
                if (extra.isEnabled() && boost.isEnabled()) {
                    if (!timer.reach(135) && timer.reach(20)) {
                        getMc().timer.timerSpeed = 3.5f;
                    } else {
                        getMc().timer.timerSpeed = 1f;
                    }
                    if (getMc().thePlayer.hurtResistantTime == 19 && level < 20) {
                        //getMc().timer.timerSpeed = 3.6f;
                        timer.reset();
                    }
                }
                if (boost.isEnabled()) {
                    final float motionY = 0.42f + (getMc().thePlayer.isPotionActive(Potion.jump) ? ((getMc().thePlayer.getActivePotionEffect(Potion.jump).getAmplifier() + 1) * 0.1F) : 0);
                    switch (boostModes.getValue()) {
                        case NORMAL:
                        case DAMAGE:
                            if (getMc().thePlayer.isMoving()) {
                                if (level != 1) {
                                    if (level == 2) {
                                        //getMc().timer.timerSpeed = Math.max(1, 3.5F);
                                        ++level;
                                        moveSpeed *= getMc().thePlayer.isPotionActive(Potion.moveSpeed) ? flyspeed.getValue() - 0.3 : flyspeed.getValue();
                                        //Printer.print("2: "+moveSpeed);
                                    } else if (level == 3) {
                                        ++level;
                                        double difference = (boostModes.getValue() == BoostModes.DAMAGE ? 0.01 : 0.1D) * (lastDist - getBaseMoveSpeed());
                                        moveSpeed = lastDist - difference;
                                    } else {
                                        level++;
                                        if (getMc().theWorld.getCollidingBoundingBoxes(getMc().thePlayer, getMc().thePlayer.getEntityBoundingBox().offset(0.0D, getMc().thePlayer.motionY, 0.0D)).size() > 0 || getMc().thePlayer.isCollidedVertically) {
                                            level = 1;
                                        }
                                        moveSpeed = lastDist - lastDist / 159D;
                                    }
                                } else if (getMc().thePlayer.hurtResistantTime == 19 || boostModes.getValue() == BoostModes.NORMAL) {
                                    event.setY(getMc().thePlayer.motionY = motionY);
                                    level = 2;
                                    double boost = getMc().thePlayer.isPotionActive(Potion.moveSpeed) ? 1.5 : 1.62;
                                    moveSpeed = boost * getBaseMoveSpeed() - 0.01D;
                                }
                            } else {
                                moveSpeed = 0;
                            }
                            //Printer.print("f: "+moveSpeed + " " + level);
                            moveSpeed = Math.max(moveSpeed, getBaseMoveSpeed());
                            if (level == 1 && getMc().thePlayer.hurtResistantTime != 19 && boostModes.getValue() != BoostModes.NORMAL) moveSpeed = 0.011;
                            MoveUtil.setMoveSpeed(event, moveSpeed);
                            break;
                        case WOWOMG:
                            if (getMc().thePlayer.isMoving()) {
                                if (level != 1) {
                                    if (level == 2) {
                                        //getMc().timer.timerSpeed = Math.max(1, 3.5F);
                                        ++level;
                                        moveSpeed *= getMc().thePlayer.isPotionActive(Potion.moveSpeed) ? flyspeed.getValue() - 0.3 : flyspeed.getValue();
                                        //Printer.print("2: "+moveSpeed);
                                    } else if (level == 3) {
                                        ++level;
                                        double difference = 0.01 * (lastDist - getBaseMoveSpeed());
                                        moveSpeed = lastDist - difference;
                                    } else {
                                        level++;
                                        if (getMc().theWorld.getCollidingBoundingBoxes(getMc().thePlayer, getMc().thePlayer.getEntityBoundingBox().offset(0.0D, getMc().thePlayer.motionY, 0.0D)).size() > 0 || getMc().thePlayer.isCollidedVertically) {
                                            level = 1;
                                        }
                                        moveSpeed = moveSpeed - moveSpeed / 159.9D;
                                    }
                                } else {
                                    event.setY(getMc().thePlayer.motionY = motionY);
                                    level = 2;
                                    double boost = getMc().thePlayer.isPotionActive(Potion.moveSpeed) ? 1.55 : 1.62;
                                    moveSpeed = boost * getBaseMoveSpeed() - 0.01D;
                                }
                            } else {
                                moveSpeed = 0;
                            }
                          //  Printer.print("f: "+moveSpeed + " " + level);
                            moveSpeed = Math.max(moveSpeed, getBaseMoveSpeed());
                            MoveUtil.setMoveSpeed(event, moveSpeed);
                            break;
                    }
                } else {
                    MoveUtil.setMoveSpeed(event, getBaseMoveSpeed());
                    //  getMc().timer.timerSpeed = 0.5f;
                    //MoveUtil.TP(event, 0.28, 0);
                }
                break;
            default:
                break;
        }
    }

    private double getBaseMoveSpeed() {
        double n = 0.2873;
        if (getMc().thePlayer.isPotionActive(Potion.moveSpeed)) {
            n *= 1.0 + 0.2 * (getMc().thePlayer.getActivePotionEffect(Potion.moveSpeed).getAmplifier() + 1);
        }
        return n;
    }

    @Override
    public void onEnable() {
        if (getMc().thePlayer == null || getMc().theWorld == null) return;
        wait = 0;
        level = 0;
        lastDist = 0.0D;
    }

    private void setMoveSpeed(MotionEvent event, double speed) {
        double forward = getMc().thePlayer.movementInput.moveForward;
        double strafe = getMc().thePlayer.movementInput.moveStrafe;
        float yaw = getMc().thePlayer.rotationYaw;
        if (forward == 0.0D && strafe == 0.0D) {
            event.setX(0);
            event.setZ(0);
        } else {
            if (forward != 0.0D) {
                if (strafe > 0.0D) {
                    yaw += forward > 0.0D ? -45 : 45;
                } else if (strafe < 0.0D) {
                    yaw += forward > 0.0D ? 45 : -45;
                }

                strafe = 0.0D;
                if (forward > 0.0D) {
                    forward = 1.0D;
                } else if (forward < 0.0D) {
                    forward = -1.0D;
                }
            }

            event.setX(forward * speed * -Math.sin(Math.toRadians(yaw)) + strafe * speed * Math.cos(Math.toRadians(yaw)));
            event.setZ(forward * speed * Math.cos(Math.toRadians(yaw)) - strafe * speed * -Math.sin(Math.toRadians(yaw)));
        }
    }
    
    public void gija(float speed) {
		mc.thePlayer.motionX = (-(Math.sin(aan()) * speed));
		mc.thePlayer.motionZ = (Math.cos(aan()) * speed);
	}
    
    public float aan() {
		float var1 = mc.thePlayer.rotationYaw;
		if (mc.thePlayer.moveForward < 0.0F) {
			var1 += 180.0F;
		}
		float forward = 1.0F;
		if (mc.thePlayer.moveForward < 0.0F) {
			forward = -0.5F;
		} else if (mc.thePlayer.moveForward > 0.0F) {
			forward = 0.5F;
		}
		if (mc.thePlayer.moveStrafing > 0.0F) {
			var1 -= 90.0F * forward;
		}
		if (mc.thePlayer.moveStrafing < 0.0F) {
			var1 += 90.0F * forward;
		}
		var1 *= 0.017453292F;

		return var1;
	}
    
    
}

