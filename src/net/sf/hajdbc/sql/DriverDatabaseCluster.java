/*
 * HA-JDBC: High-Availability JDBC
 * Copyright (c) 2004-2007 Paul Ferraro
 * 
 * This library is free software; you can redistribute it and/or modify it 
 * under the terms of the GNU Lesser General Public License as published by the 
 * Free Software Foundation; either version 2.1 of the License, or (at your 
 * option) any later version.
 * 
 * This library is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or 
 * FITNESS FOR A PARTICULAR PURPOSE. See the GNU Lesser General Public License 
 * for more details.
 * 
 * You should have received a copy of the GNU Lesser General Public License
 * along with this library; if not, write to the Free Software Foundation, 
 * Inc., 59 Temple Place, Suite 330, Boston, MA 02111-1307 USA
 * 
 * Contact: ferraro@users.sourceforge.net
 */
package net.sf.hajdbc.sql;

import java.net.URL;
import java.sql.Driver;

import org.jibx.runtime.IUnmarshallingContext;
import org.jibx.runtime.impl.UnmarshallingContext;

/**
 * @author Paul Ferraro
 *
 */
public class DriverDatabaseCluster extends AbstractDatabaseCluster<Driver> implements DriverDatabaseClusterMBean
{
	public static DriverDatabaseCluster extractDatabaseCluster(IUnmarshallingContext context)
	{
		return DriverDatabaseCluster.class.cast(UnmarshallingContext.class.cast(context).getUserContext());
	}
	
	/**
	 * Constructs a new DriverDatabaseCluster
	 * @param id
	 * @param url
	 */
	public DriverDatabaseCluster(String id, URL url)
	{
		super(id, url);
	}

	/**
	 * @see net.sf.hajdbc.sql.DriverDatabaseClusterMBean#add(java.lang.String, java.lang.String, java.lang.String)
	 */
	public void add(String databaseId, String driver, String url)
	{
		DriverDatabase database = new DriverDatabase();
		
		database.setId(databaseId);
		database.setDriver(driver);
		database.setUrl(url);
		
		this.register(database, database.getInactiveMBean());
		
		this.add(database);
	}
}