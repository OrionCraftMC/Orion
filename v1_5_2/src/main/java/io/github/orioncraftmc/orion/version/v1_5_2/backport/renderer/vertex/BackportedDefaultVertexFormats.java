/*
 * Copyright (C) 2021 OrionCraftMC
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <https://www.gnu.org/licenses/>.
 */

package io.github.orioncraftmc.orion.version.v1_5_2.backport.renderer.vertex;

public class BackportedDefaultVertexFormats {
    public static BackportedDefaultVertexFormats instance = new BackportedDefaultVertexFormats();

    public static final BackportedVertexFormat BLOCK = new BackportedVertexFormat();
    public static final BackportedVertexFormat ITEM = new BackportedVertexFormat();
    public static final BackportedVertexFormat OLDMODEL_POSITION_TEX_NORMAL = new BackportedVertexFormat();
    public static final BackportedVertexFormat PARTICLE_POSITION_TEX_COLOR_LMAP = new BackportedVertexFormat();
    public static final BackportedVertexFormat POSITION = new BackportedVertexFormat();
    public static final BackportedVertexFormat POSITION_COLOR = new BackportedVertexFormat();
    public static final BackportedVertexFormat POSITION_TEX = new BackportedVertexFormat();
    public static final BackportedVertexFormat POSITION_NORMAL = new BackportedVertexFormat();
    public static final BackportedVertexFormat POSITION_TEX_COLOR = new BackportedVertexFormat();
    public static final BackportedVertexFormat POSITION_TEX_NORMAL = new BackportedVertexFormat();
    public static final BackportedVertexFormat POSITION_TEX_LMAP_COLOR = new BackportedVertexFormat();
    public static final BackportedVertexFormat POSITION_TEX_COLOR_NORMAL = new BackportedVertexFormat();
    public static final BackportedVertexFormatElement POSITION_3F;
    public static final BackportedVertexFormatElement COLOR_4UB;
    public static final BackportedVertexFormatElement TEX_2F;
    public static final BackportedVertexFormatElement TEX_2S;
    public static final BackportedVertexFormatElement NORMAL_3B;
    public static final BackportedVertexFormatElement PADDING_1B;

    static {
        POSITION_3F = new BackportedVertexFormatElement(0, BackportedVertexFormatElement.EnumType.FLOAT, BackportedVertexFormatElement.EnumUsage.POSITION, 3);
        COLOR_4UB = new BackportedVertexFormatElement(0, BackportedVertexFormatElement.EnumType.UBYTE, BackportedVertexFormatElement.EnumUsage.COLOR, 4);
        TEX_2F = new BackportedVertexFormatElement(0, BackportedVertexFormatElement.EnumType.FLOAT, BackportedVertexFormatElement.EnumUsage.UV, 2);
        TEX_2S = new BackportedVertexFormatElement(1, BackportedVertexFormatElement.EnumType.SHORT, BackportedVertexFormatElement.EnumUsage.UV, 2);
        NORMAL_3B = new BackportedVertexFormatElement(0, BackportedVertexFormatElement.EnumType.BYTE, BackportedVertexFormatElement.EnumUsage.NORMAL, 3);
        PADDING_1B = new BackportedVertexFormatElement(0, BackportedVertexFormatElement.EnumType.BYTE, BackportedVertexFormatElement.EnumUsage.PADDING, 1);
        BLOCK.addElement(POSITION_3F);
        BLOCK.addElement(COLOR_4UB);
        BLOCK.addElement(TEX_2F);
        BLOCK.addElement(TEX_2S);
        ITEM.addElement(POSITION_3F);
        ITEM.addElement(COLOR_4UB);
        ITEM.addElement(TEX_2F);
        ITEM.addElement(NORMAL_3B);
        ITEM.addElement(PADDING_1B);
        OLDMODEL_POSITION_TEX_NORMAL.addElement(POSITION_3F);
        OLDMODEL_POSITION_TEX_NORMAL.addElement(TEX_2F);
        OLDMODEL_POSITION_TEX_NORMAL.addElement(NORMAL_3B);
        OLDMODEL_POSITION_TEX_NORMAL.addElement(PADDING_1B);
        PARTICLE_POSITION_TEX_COLOR_LMAP.addElement(POSITION_3F);
        PARTICLE_POSITION_TEX_COLOR_LMAP.addElement(TEX_2F);
        PARTICLE_POSITION_TEX_COLOR_LMAP.addElement(COLOR_4UB);
        PARTICLE_POSITION_TEX_COLOR_LMAP.addElement(TEX_2S);
        POSITION.addElement(POSITION_3F);
        POSITION_COLOR.addElement(POSITION_3F);
        POSITION_COLOR.addElement(COLOR_4UB);
        POSITION_TEX.addElement(POSITION_3F);
        POSITION_TEX.addElement(TEX_2F);
        POSITION_NORMAL.addElement(POSITION_3F);
        POSITION_NORMAL.addElement(NORMAL_3B);
        POSITION_NORMAL.addElement(PADDING_1B);
        POSITION_TEX_COLOR.addElement(POSITION_3F);
        POSITION_TEX_COLOR.addElement(TEX_2F);
        POSITION_TEX_COLOR.addElement(COLOR_4UB);
        POSITION_TEX_NORMAL.addElement(POSITION_3F);
        POSITION_TEX_NORMAL.addElement(TEX_2F);
        POSITION_TEX_NORMAL.addElement(NORMAL_3B);
        POSITION_TEX_NORMAL.addElement(PADDING_1B);
        POSITION_TEX_LMAP_COLOR.addElement(POSITION_3F);
        POSITION_TEX_LMAP_COLOR.addElement(TEX_2F);
        POSITION_TEX_LMAP_COLOR.addElement(TEX_2S);
        POSITION_TEX_LMAP_COLOR.addElement(COLOR_4UB);
        POSITION_TEX_COLOR_NORMAL.addElement(POSITION_3F);
        POSITION_TEX_COLOR_NORMAL.addElement(TEX_2F);
        POSITION_TEX_COLOR_NORMAL.addElement(COLOR_4UB);
        POSITION_TEX_COLOR_NORMAL.addElement(NORMAL_3B);
        POSITION_TEX_COLOR_NORMAL.addElement(PADDING_1B);
    }
}
