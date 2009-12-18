/*
 * This program is free software; you can redistribute it and/or modify it under the
 * terms of the GNU Lesser General Public License, version 2.1 as published by the Free Software
 * Foundation.
 *
 * You should have received a copy of the GNU Lesser General Public License along with this
 * program; if not, you can obtain a copy at http://www.gnu.org/licenses/old-licenses/lgpl-2.1.html
 * or from the Free Software Foundation, Inc.,
 * 51 Franklin Street, Fifth Floor, Boston, MA 02110-1301 USA.
 *
 * This program is distributed in the hope that it will be useful, but WITHOUT ANY WARRANTY;
 * without even the implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.
 * See the GNU Lesser General Public License for more details.
 *
 * Copyright (c) 2006 - 2009 Pentaho Corporation..  All rights reserved.
 */
package org.pentaho.metadata.model.olap;

import java.util.ArrayList;
import java.util.List;

import org.pentaho.metadata.model.LogicalColumn;

public class OlapHierarchyLevel implements Cloneable {
  private String name;
  private LogicalColumn referenceColumn; // Also has the logical table
                                         // of-course.
  private List<LogicalColumn> logicalColumns;
  private boolean havingUniqueMembers;

  private OlapHierarchy olapHierarchy;

  public OlapHierarchyLevel(OlapHierarchy olapHierarchy) {
    super();
    this.olapHierarchy = olapHierarchy;
    logicalColumns = new ArrayList<LogicalColumn>();
  }

  /**
   * @param name
   * @param referenceColumn
   * @param logicalColumns
   */
  public OlapHierarchyLevel(OlapHierarchy olapHierarchy, String name, LogicalColumn referenceColumn,
      List<LogicalColumn> logicalColumns) {
    this(olapHierarchy);
    this.name = name;
    this.referenceColumn = referenceColumn;
    this.logicalColumns = logicalColumns;
  }

  public Object clone() {
    // weak link again to the parent
    OlapHierarchyLevel hierarchyLevel = new OlapHierarchyLevel(olapHierarchy);

    hierarchyLevel.name = name;
    if (referenceColumn != null)
      hierarchyLevel.referenceColumn = (LogicalColumn) referenceColumn.clone();
    for (int i = 0; i < logicalColumns.size(); i++) {
      LogicalColumn logicalColumn = (LogicalColumn) logicalColumns.get(i);
      hierarchyLevel.logicalColumns.add((LogicalColumn) logicalColumn.clone());
    }
    hierarchyLevel.havingUniqueMembers = havingUniqueMembers;

    return hierarchyLevel;
  }

  public boolean equals(Object obj) {
    return name.equals(((OlapHierarchyLevel) obj).getName());
  }

  /**
   * @return the logicalColumns
   */
  public List<LogicalColumn> getLogicalColumns() {
    return logicalColumns;
  }

  /**
   * @param logicalColumns
   *          the logicalColumns to set
   */
  public void setLogicalColumns(List<LogicalColumn> logicalColumns) {
    this.logicalColumns = logicalColumns;
  }

  /**
   * @return the name
   */
  public String getName() {
    return name;
  }

  /**
   * @param name
   *          the name to set
   */
  public void setName(String name) {
    this.name = name;
  }

  /**
   * @return the referenceColumn
   */
  public LogicalColumn getReferenceColumn() {
    return referenceColumn;
  }

  /**
   * @param referenceColumn
   *          the referenceColumn to set
   */
  public void setReferenceColumn(LogicalColumn referenceColumn) {
    this.referenceColumn = referenceColumn;
  }

  public LogicalColumn findLogicalColumn(String locale, String thisName) {
    if (referenceColumn != null && referenceColumn.getName(locale).equalsIgnoreCase(thisName))
      return referenceColumn;

    for (int i = 0; i < logicalColumns.size(); i++) {
      LogicalColumn column = (LogicalColumn) logicalColumns.get(i);
      if (column.getName(locale).equalsIgnoreCase(thisName))
        return column;
    }
    return null;
  }

  /**
   * @return the olapHierarchy
   */
  public OlapHierarchy getOlapHierarchy() {
    return olapHierarchy;
  }

  /**
   * @param olapHierarchy
   *          the olapHierarchy to set
   */
  public void setOlapHierarchy(OlapHierarchy olapHierarchy) {
    this.olapHierarchy = olapHierarchy;
  }

  /**
   * @return the havingUniqueMembers
   */
  public boolean isHavingUniqueMembers() {
    return havingUniqueMembers;
  }

  /**
   * @param havingUniqueMembers
   *          the havingUniqueMembers to set
   */
  public void setHavingUniqueMembers(boolean havingUniqueMembers) {
    this.havingUniqueMembers = havingUniqueMembers;
  }
}