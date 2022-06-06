package com.leonidas4G.model;

import lombok.*;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "packets")
@Getter
@Setter
@ToString
@AllArgsConstructor
@RequiredArgsConstructor
public class Packet {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "indice")
    private Integer indice;

    @Column(name = "temps")
    private String temps;

    @Column(name = "protocol")
    private String protocol;

    @Column(name = "captured_size")
    private Integer captureSize;

    @Column(name = "src")
    private String src;

    @Column(name = "dst")
    private String dst;

    @Column(name = "mcc")
    private String mcc;

    @Column(name = "mnc")
    private String mnc;

    @Column(name = "channel")
    private String channel;

    public Packet(Integer indice, String temps, String protocol, Integer captureSize, String src, String dst, String mcc, String mnc, String channel) {
        this.indice = indice;
        this.temps = temps;
        this.protocol = protocol;
        this.captureSize = captureSize;
        this.src = src;
        this.dst = dst;
        this.mcc = mcc;
        this.mnc = mnc;
        this.channel = channel;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Packet packet = (Packet) o;
        return Objects.equals(id, packet.id) && Objects.equals(indice, packet.indice) && Objects.equals(temps, packet.temps) && Objects.equals(protocol, packet.protocol) && Objects.equals(captureSize, packet.captureSize) && Objects.equals(src, packet.src) && Objects.equals(dst, packet.dst) && Objects.equals(mcc, packet.mcc) && Objects.equals(mnc, packet.mnc) && Objects.equals(channel, packet.channel);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, indice, temps, protocol, captureSize, src, dst, mcc, mnc, channel);
    }
}


