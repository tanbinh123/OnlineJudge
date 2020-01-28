package cn.edu.zjnu.learncs.entity.oj;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;

import javax.persistence.*;
import java.util.List;

@Slf4j
@Data
@Entity
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Problem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(length = 50, unique = true, nullable = false)
    private String title = "";
    @Column(columnDefinition = "LONGTEXT", nullable = false)
    private String description = "";
    @Column(columnDefinition = "LONGTEXT")
    private String input = "";
    @Column(columnDefinition = "LONGTEXT")
    private String output = "";
    @Column(columnDefinition = "LONGTEXT")
    private String sampleInput = "";
    @Column(columnDefinition = "LONGTEXT")
    private String sampleOutput = "";
    @Column(columnDefinition = "LONGTEXT")
    private String hint = "";
    @Column(columnDefinition = "LONGTEXT")
    private String source = "";
    @Column(nullable = false, columnDefinition = "int default 1000")
    private Integer timeLimit = 1;
    @Column(nullable = false, columnDefinition = "int default 65536")
    private Integer memoryLimit = 1;
    @Column(nullable = false)
    private Boolean active = false;
    @Column(columnDefinition = "integer default 0")
    private Integer score = 0;
    @ManyToMany(fetch = FetchType.EAGER)
    private List<Tag> tags;
    @Column(columnDefinition = "integer default 0")
    private Integer submit = 0;
    @Column(columnDefinition = "integer default 0")
    private Integer accepted = 0;

    public Problem() {
    }

    public Problem(String title, String description, String input, String output, String sampleInput, String sampleOutput, String hint, String source, Integer timeLimit, Integer memoryLimit, Integer score) {
        this.title = title;
        this.description = description;
        this.input = input;
        this.output = output;
        this.sampleInput = sampleInput;
        this.sampleOutput = sampleOutput;
        this.hint = hint;
        this.source = source;
        this.timeLimit = timeLimit;
        this.memoryLimit = memoryLimit;
        accepted = 0;
        submit = 0;
        active = false;
        this.score = score;
    }

    @Override
    public String toString() {
        return "Problem{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", input='" + input + '\'' +
                ", output='" + output + '\'' +
                ", sampleInput='" + sampleInput + '\'' +
                ", sampleOutput='" + sampleOutput + '\'' +
                ", hint='" + hint + '\'' +
                ", source='" + source + '\'' +
                ", timeLimit=" + timeLimit +
                ", memoryLimit=" + memoryLimit +
                ", active=" + active +
                ", submit=" + submit +
                ", accepted=" + accepted +
                '}';
    }

    public String getRatio() {
        try {
            if (this.submit == 0) return "0%";
            double ratio = this.accepted * 1.0 / this.submit * 100.0;
            return String.format("%.2f%%", ratio);
        } catch (Exception e) {
            return null;
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Problem problem = (Problem) o;
        return getId().equals(problem.getId());
    }

    @Override
    public int hashCode() {
        return getId().hashCode();
    }

    public String getTitle() {
        return title == null ? "" : title;
    }

    public String getDescription() {
        return description == null ? "" : description;
    }

    public String getInput() {
        return input == null ? "" : input;
    }

    public String getOutput() {
        return output == null ? "" : output;
    }

    public String getSampleInput() {
        return sampleInput == null ? "" : sampleInput;
    }

    public String getSampleOutput() {
        return sampleOutput == null ? "" : sampleOutput;
    }

    public String getHint() {
        return hint == null ? "" : hint;
    }

    public String getSource() {
        return source == null ? "" : source;
    }

    public Integer getTimeLimit() {
        return timeLimit == null ? 1 : timeLimit;
    }

    public Integer getMemoryLimit() {
        return memoryLimit == null ? 1 : memoryLimit;
    }

}
