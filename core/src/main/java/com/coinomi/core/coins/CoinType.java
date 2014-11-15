package com.coinomi.core.coins;


import com.google.bitcoin.core.Coin;
import com.google.bitcoin.core.NetworkParameters;
import com.google.bitcoin.crypto.ChildNumber;
import com.google.bitcoin.crypto.HDUtils;

import java.io.Serializable;
import java.util.List;

import javax.annotation.Nullable;

import static com.google.common.base.Preconditions.checkNotNull;

/**
 * @author Giannis Dzegoutanis
 */
abstract public class CoinType extends NetworkParameters implements Serializable{
    private static final long serialVersionUID = 1L;

    private static final String BIP_44_KEY_PATH = "44'/%d'/%d'";

    protected long uid = -1;
    protected String name;
    protected String symbol;
    protected String uriScheme;
    protected int bip44Index;
    protected Coin feePerKb;
    protected Coin minNonDust;

    public long getUid() {
        return uid;
    }

    public String getName() {
        return name;
    }

    public String getSymbol() {
        return symbol;
    }

    public String getUriScheme() {
        return uriScheme;
    }

    public int getBip44Index() {
        return bip44Index;
    }

    public Coin getFeePerKb() {
        return checkNotNull(feePerKb);
    }
    public Coin getMinNonDust() {
        return checkNotNull(minNonDust);
    }

    public List<ChildNumber> getBip44Path(int account) {
        String path = String.format(BIP_44_KEY_PATH, bip44Index, account);
        return HDUtils.parsePath(path);
    }

    @Override
    public String getPaymentProtocolId() {
        throw new RuntimeException("Method not implemented");
    }

    @Nullable
    public static CoinType fromID(String id) {
        return CoinID.fromId(id).getCoinType();
    }

    @Override
    public String toString() {
        return "Coin{" +
                "name='" + name + '\'' +
                ", symbol='" + symbol + '\'' +
                ", bip44Index=" + bip44Index +
                '}';
    }
}
