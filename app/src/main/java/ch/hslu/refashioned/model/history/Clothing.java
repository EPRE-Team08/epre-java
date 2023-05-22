package ch.hslu.refashioned.model.history;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.time.LocalDateTime;

@Entity
public class Clothing {

    @PrimaryKey
    public int id;
    @ColumnInfo(name = "scan_date_time")
    public LocalDateTime scanDateTime;

    @ColumnInfo(name = "image_path")
    public String imagePath;

    @ColumnInfo(name = "brand")
    public Brand brand;

    @ColumnInfo(name = "sustainability_score")
    public int sustainabilityScore;
}
