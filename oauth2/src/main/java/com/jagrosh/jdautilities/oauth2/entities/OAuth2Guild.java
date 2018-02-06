/*
 * Copyright 2016-2018 John Grosh (jagrosh) & Kaidan Gustave (TheMonitorLizard)
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.jagrosh.jdautilities.oauth2.entities;

import java.util.Collections;
import java.util.List;
import net.dv8tion.jda.core.Permission;
import net.dv8tion.jda.core.entities.ISnowflake;

/**
 *
 * @author John Grosh (john.a.grosh@gmail.com)
 */
public class OAuth2Guild implements ISnowflake
{
    private final long id;
    private final String name, icon;
    private final boolean owner;
    private final int permissions;
    
    public OAuth2Guild(long id, String name, String icon, boolean owner, int permissions)
    {
        this.id = id;
        this.name = name;
        this.icon = icon;
        this.owner = owner;
        this.permissions = permissions;
    }
    
    @Override
    public long getIdLong()
    {
        return id;
    }
    
    public String getName()
    {
        return name;
    }
    
    public String getIconId()
    {
        return icon;
    }
    
    public String getIconUrl()
    {
        return icon == null ? null : "https://cdn.discordapp.com/icons/" + id + "/" + icon + ".jpg";
    }
    
    public int getPermissionsRaw()
    {
        return permissions;
    }
    
    public List<Permission> getPermissions()
    {
        return Collections.unmodifiableList(Permission.getPermissions(permissions));
    }
    
    public boolean isOwner()
    {
        return owner;
    }
    
    public boolean hasPermission(Permission... perms)
    {
        if(owner)
            return true;
        for (Permission perm : perms)
        {
            final long rawValue = perm.getRawValue();
            if ((permissions & rawValue) != rawValue)
                return false;
        }
        return true;
    }
}