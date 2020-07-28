// ============================================================================
//
// Copyright (C) 2006-2020 Talend Inc. - www.talend.com
//
// This source code is available under agreement available at
// %InstallDIR%\features\org.talend.rcp.branding.%PRODUCTNAME%\%PRODUCTNAME%license.txt
//
// You should have received a copy of the agreement
// along with this program; if not, write to Talend SA
// 9 rue Pages 92150 Suresnes, France
//
// ============================================================================
package org.talend.dataquality.indicators.mapdb;

import org.talend.dataquality.indicators.mapdb.DBMapCompartor.NullCompareStrategy;

/**
 * The class is working for tmatchGroup it will delete cache file when clear DB
 */
public class BlockKeyDBSet<E> extends DBSet<E> {

    Boolean isRemoveCacheByComponent = false;

    /**
     * Constructor of BlockKeyDBSet
     */
    public BlockKeyDBSet() {
        super();
    }

    /**
     * Constructor of BlockKeyDBSet
     * 
     * @param parameter
     */
    public BlockKeyDBSet(DBMapParameter parameter) {
        super(parameter);
    }

    /**
     * Constructor of BlockKeyDBSet
     * 
     * @param compareStrategy
     */
    public BlockKeyDBSet(NullCompareStrategy compareStrategy) {
        super(compareStrategy);
    }

    /**
     * Constructor of BlockKeyDBSet
     * 
     * @param compareStrategy
     */
    public BlockKeyDBSet(NullCompareStrategy compareStrategy, boolean isRemoveCacheByComponent) {
        super(compareStrategy);
        this.isRemoveCacheByComponent = isRemoveCacheByComponent;
    }

    /**
     * Constructor of BlockKeyDBSet
     * 
     * @param parentFullPathStr
     * @param fileName
     * @param setName
     * @param limSize
     */
    public BlockKeyDBSet(String parentFullPathStr, String fileName, String setName, Long limSize) {
        super(parentFullPathStr, fileName, setName, limSize);
    }

    /**
     * Constructor of BlockKeyDBSet
     * 
     * @param parentFullPathStr
     * @param fileName
     * @param setName
     */
    public BlockKeyDBSet(String parentFullPathStr, String fileName, String setName) {
        super(parentFullPathStr, fileName, setName);
    }

    /**
     * Constructor of BlockKeyDBSet
     * 
     * @param parentFullPathStr
     * @param fileName
     */
    public BlockKeyDBSet(String parentFullPathStr, String fileName) {
        super(parentFullPathStr, fileName);
    }

    /**
     * clear DB and close DB(the cache file will be deleted when the db is closed)
     */
    @Override
    public void clear() {
        if (!getDB().isClosed()) {
            dbSet.clear();
            this.getDB().delete(setName);
            if (isRemoveCacheByComponent) {
                this.getDB().close();
                System.out.println("close DB");
            }
        }
    }

}
