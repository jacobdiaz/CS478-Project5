/*
 * This file is auto-generated.  DO NOT MODIFY.
 */
package course.examples.Services.KeyCommon;
public interface KeyGenerator extends android.os.IInterface
{
  /** Default implementation for KeyGenerator. */
  public static class Default implements course.examples.Services.KeyCommon.KeyGenerator
  {
    @Override public java.util.List<java.lang.String> getSongNames() throws android.os.RemoteException
    {
      return null;
    }
    @Override public java.util.List<java.lang.String> getArtists() throws android.os.RemoteException
    {
      return null;
    }
    @Override public java.util.List<java.lang.String> getSongUrls() throws android.os.RemoteException
    {
      return null;
    }
    @Override
    public android.os.IBinder asBinder() {
      return null;
    }
  }
  /** Local-side IPC implementation stub class. */
  public static abstract class Stub extends android.os.Binder implements course.examples.Services.KeyCommon.KeyGenerator
  {
    private static final java.lang.String DESCRIPTOR = "course.examples.Services.KeyCommon.KeyGenerator";
    /** Construct the stub at attach it to the interface. */
    public Stub()
    {
      this.attachInterface(this, DESCRIPTOR);
    }
    /**
     * Cast an IBinder object into an course.examples.Services.KeyCommon.KeyGenerator interface,
     * generating a proxy if needed.
     */
    public static course.examples.Services.KeyCommon.KeyGenerator asInterface(android.os.IBinder obj)
    {
      if ((obj==null)) {
        return null;
      }
      android.os.IInterface iin = obj.queryLocalInterface(DESCRIPTOR);
      if (((iin!=null)&&(iin instanceof course.examples.Services.KeyCommon.KeyGenerator))) {
        return ((course.examples.Services.KeyCommon.KeyGenerator)iin);
      }
      return new course.examples.Services.KeyCommon.KeyGenerator.Stub.Proxy(obj);
    }
    @Override public android.os.IBinder asBinder()
    {
      return this;
    }
    @Override public boolean onTransact(int code, android.os.Parcel data, android.os.Parcel reply, int flags) throws android.os.RemoteException
    {
      java.lang.String descriptor = DESCRIPTOR;
      switch (code)
      {
        case INTERFACE_TRANSACTION:
        {
          reply.writeString(descriptor);
          return true;
        }
        case TRANSACTION_getSongNames:
        {
          data.enforceInterface(descriptor);
          java.util.List<java.lang.String> _result = this.getSongNames();
          reply.writeNoException();
          reply.writeStringList(_result);
          return true;
        }
        case TRANSACTION_getArtists:
        {
          data.enforceInterface(descriptor);
          java.util.List<java.lang.String> _result = this.getArtists();
          reply.writeNoException();
          reply.writeStringList(_result);
          return true;
        }
        case TRANSACTION_getSongUrls:
        {
          data.enforceInterface(descriptor);
          java.util.List<java.lang.String> _result = this.getSongUrls();
          reply.writeNoException();
          reply.writeStringList(_result);
          return true;
        }
        default:
        {
          return super.onTransact(code, data, reply, flags);
        }
      }
    }
    private static class Proxy implements course.examples.Services.KeyCommon.KeyGenerator
    {
      private android.os.IBinder mRemote;
      Proxy(android.os.IBinder remote)
      {
        mRemote = remote;
      }
      @Override public android.os.IBinder asBinder()
      {
        return mRemote;
      }
      public java.lang.String getInterfaceDescriptor()
      {
        return DESCRIPTOR;
      }
      @Override public java.util.List<java.lang.String> getSongNames() throws android.os.RemoteException
      {
        android.os.Parcel _data = android.os.Parcel.obtain();
        android.os.Parcel _reply = android.os.Parcel.obtain();
        java.util.List<java.lang.String> _result;
        try {
          _data.writeInterfaceToken(DESCRIPTOR);
          boolean _status = mRemote.transact(Stub.TRANSACTION_getSongNames, _data, _reply, 0);
          if (!_status && getDefaultImpl() != null) {
            return getDefaultImpl().getSongNames();
          }
          _reply.readException();
          _result = _reply.createStringArrayList();
        }
        finally {
          _reply.recycle();
          _data.recycle();
        }
        return _result;
      }
      @Override public java.util.List<java.lang.String> getArtists() throws android.os.RemoteException
      {
        android.os.Parcel _data = android.os.Parcel.obtain();
        android.os.Parcel _reply = android.os.Parcel.obtain();
        java.util.List<java.lang.String> _result;
        try {
          _data.writeInterfaceToken(DESCRIPTOR);
          boolean _status = mRemote.transact(Stub.TRANSACTION_getArtists, _data, _reply, 0);
          if (!_status && getDefaultImpl() != null) {
            return getDefaultImpl().getArtists();
          }
          _reply.readException();
          _result = _reply.createStringArrayList();
        }
        finally {
          _reply.recycle();
          _data.recycle();
        }
        return _result;
      }
      @Override public java.util.List<java.lang.String> getSongUrls() throws android.os.RemoteException
      {
        android.os.Parcel _data = android.os.Parcel.obtain();
        android.os.Parcel _reply = android.os.Parcel.obtain();
        java.util.List<java.lang.String> _result;
        try {
          _data.writeInterfaceToken(DESCRIPTOR);
          boolean _status = mRemote.transact(Stub.TRANSACTION_getSongUrls, _data, _reply, 0);
          if (!_status && getDefaultImpl() != null) {
            return getDefaultImpl().getSongUrls();
          }
          _reply.readException();
          _result = _reply.createStringArrayList();
        }
        finally {
          _reply.recycle();
          _data.recycle();
        }
        return _result;
      }
      public static course.examples.Services.KeyCommon.KeyGenerator sDefaultImpl;
    }
    static final int TRANSACTION_getSongNames = (android.os.IBinder.FIRST_CALL_TRANSACTION + 0);
    static final int TRANSACTION_getArtists = (android.os.IBinder.FIRST_CALL_TRANSACTION + 1);
    static final int TRANSACTION_getSongUrls = (android.os.IBinder.FIRST_CALL_TRANSACTION + 2);
    public static boolean setDefaultImpl(course.examples.Services.KeyCommon.KeyGenerator impl) {
      // Only one user of this interface can use this function
      // at a time. This is a heuristic to detect if two different
      // users in the same process use this function.
      if (Stub.Proxy.sDefaultImpl != null) {
        throw new IllegalStateException("setDefaultImpl() called twice");
      }
      if (impl != null) {
        Stub.Proxy.sDefaultImpl = impl;
        return true;
      }
      return false;
    }
    public static course.examples.Services.KeyCommon.KeyGenerator getDefaultImpl() {
      return Stub.Proxy.sDefaultImpl;
    }
  }
  public java.util.List<java.lang.String> getSongNames() throws android.os.RemoteException;
  public java.util.List<java.lang.String> getArtists() throws android.os.RemoteException;
  public java.util.List<java.lang.String> getSongUrls() throws android.os.RemoteException;
}
