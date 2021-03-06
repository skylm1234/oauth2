// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: CommCompoundHeroRequest.proto

package com.gejian.pixel.proto;

public final class CommCompoundHeroRequestProtobuf {
  private CommCompoundHeroRequestProtobuf() {}
  public static void registerAllExtensions(
      com.google.protobuf.ExtensionRegistryLite registry) {
  }

  public static void registerAllExtensions(
      com.google.protobuf.ExtensionRegistry registry) {
    registerAllExtensions(
        (com.google.protobuf.ExtensionRegistryLite) registry);
  }
  public interface CommCompoundHeroRequestOrBuilder extends
      // @@protoc_insertion_point(interface_extends:CommCompoundHeroRequest)
      com.google.protobuf.MessageOrBuilder {

    /**
     * <code>string hero = 10;</code>
     */
    java.lang.String getHero();
    /**
     * <code>string hero = 10;</code>
     */
    com.google.protobuf.ByteString
        getHeroBytes();
  }
  /**
   * Protobuf type {@code CommCompoundHeroRequest}
   */
  public  static final class CommCompoundHeroRequest extends
      com.google.protobuf.GeneratedMessageV3 implements
      // @@protoc_insertion_point(message_implements:CommCompoundHeroRequest)
      CommCompoundHeroRequestOrBuilder {
  private static final long serialVersionUID = 0L;
    // Use CommCompoundHeroRequest.newBuilder() to construct.
    private CommCompoundHeroRequest(com.google.protobuf.GeneratedMessageV3.Builder<?> builder) {
      super(builder);
    }
    private CommCompoundHeroRequest() {
      hero_ = "";
    }

    @java.lang.Override
    public final com.google.protobuf.UnknownFieldSet
    getUnknownFields() {
      return this.unknownFields;
    }
    private CommCompoundHeroRequest(
        com.google.protobuf.CodedInputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws com.google.protobuf.InvalidProtocolBufferException {
      this();
      if (extensionRegistry == null) {
        throw new java.lang.NullPointerException();
      }
      int mutable_bitField0_ = 0;
      com.google.protobuf.UnknownFieldSet.Builder unknownFields =
          com.google.protobuf.UnknownFieldSet.newBuilder();
      try {
        boolean done = false;
        while (!done) {
          int tag = input.readTag();
          switch (tag) {
            case 0:
              done = true;
              break;
            case 82: {
              java.lang.String s = input.readStringRequireUtf8();

              hero_ = s;
              break;
            }
            default: {
              if (!parseUnknownFieldProto3(
                  input, unknownFields, extensionRegistry, tag)) {
                done = true;
              }
              break;
            }
          }
        }
      } catch (com.google.protobuf.InvalidProtocolBufferException e) {
        throw e.setUnfinishedMessage(this);
      } catch (java.io.IOException e) {
        throw new com.google.protobuf.InvalidProtocolBufferException(
            e).setUnfinishedMessage(this);
      } finally {
        this.unknownFields = unknownFields.build();
        makeExtensionsImmutable();
      }
    }
    public static final com.google.protobuf.Descriptors.Descriptor
        getDescriptor() {
      return com.gejian.pixel.proto.CommCompoundHeroRequestProtobuf.internal_static_CommCompoundHeroRequest_descriptor;
    }

    @java.lang.Override
    protected com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
        internalGetFieldAccessorTable() {
      return com.gejian.pixel.proto.CommCompoundHeroRequestProtobuf.internal_static_CommCompoundHeroRequest_fieldAccessorTable
          .ensureFieldAccessorsInitialized(
              com.gejian.pixel.proto.CommCompoundHeroRequestProtobuf.CommCompoundHeroRequest.class, com.gejian.pixel.proto.CommCompoundHeroRequestProtobuf.CommCompoundHeroRequest.Builder.class);
    }

    public static final int HERO_FIELD_NUMBER = 10;
    private volatile java.lang.Object hero_;
    /**
     * <code>string hero = 10;</code>
     */
    public java.lang.String getHero() {
      java.lang.Object ref = hero_;
      if (ref instanceof java.lang.String) {
        return (java.lang.String) ref;
      } else {
        com.google.protobuf.ByteString bs = 
            (com.google.protobuf.ByteString) ref;
        java.lang.String s = bs.toStringUtf8();
        hero_ = s;
        return s;
      }
    }
    /**
     * <code>string hero = 10;</code>
     */
    public com.google.protobuf.ByteString
        getHeroBytes() {
      java.lang.Object ref = hero_;
      if (ref instanceof java.lang.String) {
        com.google.protobuf.ByteString b = 
            com.google.protobuf.ByteString.copyFromUtf8(
                (java.lang.String) ref);
        hero_ = b;
        return b;
      } else {
        return (com.google.protobuf.ByteString) ref;
      }
    }

    private byte memoizedIsInitialized = -1;
    @java.lang.Override
    public final boolean isInitialized() {
      byte isInitialized = memoizedIsInitialized;
      if (isInitialized == 1) return true;
      if (isInitialized == 0) return false;

      memoizedIsInitialized = 1;
      return true;
    }

    @java.lang.Override
    public void writeTo(com.google.protobuf.CodedOutputStream output)
                        throws java.io.IOException {
      if (!getHeroBytes().isEmpty()) {
        com.google.protobuf.GeneratedMessageV3.writeString(output, 10, hero_);
      }
      unknownFields.writeTo(output);
    }

    @java.lang.Override
    public int getSerializedSize() {
      int size = memoizedSize;
      if (size != -1) return size;

      size = 0;
      if (!getHeroBytes().isEmpty()) {
        size += com.google.protobuf.GeneratedMessageV3.computeStringSize(10, hero_);
      }
      size += unknownFields.getSerializedSize();
      memoizedSize = size;
      return size;
    }

    @java.lang.Override
    public boolean equals(final java.lang.Object obj) {
      if (obj == this) {
       return true;
      }
      if (!(obj instanceof com.gejian.pixel.proto.CommCompoundHeroRequestProtobuf.CommCompoundHeroRequest)) {
        return super.equals(obj);
      }
      com.gejian.pixel.proto.CommCompoundHeroRequestProtobuf.CommCompoundHeroRequest other = (com.gejian.pixel.proto.CommCompoundHeroRequestProtobuf.CommCompoundHeroRequest) obj;

      boolean result = true;
      result = result && getHero()
          .equals(other.getHero());
      result = result && unknownFields.equals(other.unknownFields);
      return result;
    }

    @java.lang.Override
    public int hashCode() {
      if (memoizedHashCode != 0) {
        return memoizedHashCode;
      }
      int hash = 41;
      hash = (19 * hash) + getDescriptor().hashCode();
      hash = (37 * hash) + HERO_FIELD_NUMBER;
      hash = (53 * hash) + getHero().hashCode();
      hash = (29 * hash) + unknownFields.hashCode();
      memoizedHashCode = hash;
      return hash;
    }

    public static com.gejian.pixel.proto.CommCompoundHeroRequestProtobuf.CommCompoundHeroRequest parseFrom(
        java.nio.ByteBuffer data)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return PARSER.parseFrom(data);
    }
    public static com.gejian.pixel.proto.CommCompoundHeroRequestProtobuf.CommCompoundHeroRequest parseFrom(
        java.nio.ByteBuffer data,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return PARSER.parseFrom(data, extensionRegistry);
    }
    public static com.gejian.pixel.proto.CommCompoundHeroRequestProtobuf.CommCompoundHeroRequest parseFrom(
        com.google.protobuf.ByteString data)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return PARSER.parseFrom(data);
    }
    public static com.gejian.pixel.proto.CommCompoundHeroRequestProtobuf.CommCompoundHeroRequest parseFrom(
        com.google.protobuf.ByteString data,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return PARSER.parseFrom(data, extensionRegistry);
    }
    public static com.gejian.pixel.proto.CommCompoundHeroRequestProtobuf.CommCompoundHeroRequest parseFrom(byte[] data)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return PARSER.parseFrom(data);
    }
    public static com.gejian.pixel.proto.CommCompoundHeroRequestProtobuf.CommCompoundHeroRequest parseFrom(
        byte[] data,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return PARSER.parseFrom(data, extensionRegistry);
    }
    public static com.gejian.pixel.proto.CommCompoundHeroRequestProtobuf.CommCompoundHeroRequest parseFrom(java.io.InputStream input)
        throws java.io.IOException {
      return com.google.protobuf.GeneratedMessageV3
          .parseWithIOException(PARSER, input);
    }
    public static com.gejian.pixel.proto.CommCompoundHeroRequestProtobuf.CommCompoundHeroRequest parseFrom(
        java.io.InputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws java.io.IOException {
      return com.google.protobuf.GeneratedMessageV3
          .parseWithIOException(PARSER, input, extensionRegistry);
    }
    public static com.gejian.pixel.proto.CommCompoundHeroRequestProtobuf.CommCompoundHeroRequest parseDelimitedFrom(java.io.InputStream input)
        throws java.io.IOException {
      return com.google.protobuf.GeneratedMessageV3
          .parseDelimitedWithIOException(PARSER, input);
    }
    public static com.gejian.pixel.proto.CommCompoundHeroRequestProtobuf.CommCompoundHeroRequest parseDelimitedFrom(
        java.io.InputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws java.io.IOException {
      return com.google.protobuf.GeneratedMessageV3
          .parseDelimitedWithIOException(PARSER, input, extensionRegistry);
    }
    public static com.gejian.pixel.proto.CommCompoundHeroRequestProtobuf.CommCompoundHeroRequest parseFrom(
        com.google.protobuf.CodedInputStream input)
        throws java.io.IOException {
      return com.google.protobuf.GeneratedMessageV3
          .parseWithIOException(PARSER, input);
    }
    public static com.gejian.pixel.proto.CommCompoundHeroRequestProtobuf.CommCompoundHeroRequest parseFrom(
        com.google.protobuf.CodedInputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws java.io.IOException {
      return com.google.protobuf.GeneratedMessageV3
          .parseWithIOException(PARSER, input, extensionRegistry);
    }

    @java.lang.Override
    public Builder newBuilderForType() { return newBuilder(); }
    public static Builder newBuilder() {
      return DEFAULT_INSTANCE.toBuilder();
    }
    public static Builder newBuilder(com.gejian.pixel.proto.CommCompoundHeroRequestProtobuf.CommCompoundHeroRequest prototype) {
      return DEFAULT_INSTANCE.toBuilder().mergeFrom(prototype);
    }
    @java.lang.Override
    public Builder toBuilder() {
      return this == DEFAULT_INSTANCE
          ? new Builder() : new Builder().mergeFrom(this);
    }

    @java.lang.Override
    protected Builder newBuilderForType(
        com.google.protobuf.GeneratedMessageV3.BuilderParent parent) {
      Builder builder = new Builder(parent);
      return builder;
    }
    /**
     * Protobuf type {@code CommCompoundHeroRequest}
     */
    public static final class Builder extends
        com.google.protobuf.GeneratedMessageV3.Builder<Builder> implements
        // @@protoc_insertion_point(builder_implements:CommCompoundHeroRequest)
        com.gejian.pixel.proto.CommCompoundHeroRequestProtobuf.CommCompoundHeroRequestOrBuilder {
      public static final com.google.protobuf.Descriptors.Descriptor
          getDescriptor() {
        return com.gejian.pixel.proto.CommCompoundHeroRequestProtobuf.internal_static_CommCompoundHeroRequest_descriptor;
      }

      @java.lang.Override
      protected com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
          internalGetFieldAccessorTable() {
        return com.gejian.pixel.proto.CommCompoundHeroRequestProtobuf.internal_static_CommCompoundHeroRequest_fieldAccessorTable
            .ensureFieldAccessorsInitialized(
                com.gejian.pixel.proto.CommCompoundHeroRequestProtobuf.CommCompoundHeroRequest.class, com.gejian.pixel.proto.CommCompoundHeroRequestProtobuf.CommCompoundHeroRequest.Builder.class);
      }

      // Construct using com.gejian.pixel.proto.CommCompoundHeroRequestProtobuf.CommCompoundHeroRequest.newBuilder()
      private Builder() {
        maybeForceBuilderInitialization();
      }

      private Builder(
          com.google.protobuf.GeneratedMessageV3.BuilderParent parent) {
        super(parent);
        maybeForceBuilderInitialization();
      }
      private void maybeForceBuilderInitialization() {
        if (com.google.protobuf.GeneratedMessageV3
                .alwaysUseFieldBuilders) {
        }
      }
      @java.lang.Override
      public Builder clear() {
        super.clear();
        hero_ = "";

        return this;
      }

      @java.lang.Override
      public com.google.protobuf.Descriptors.Descriptor
          getDescriptorForType() {
        return com.gejian.pixel.proto.CommCompoundHeroRequestProtobuf.internal_static_CommCompoundHeroRequest_descriptor;
      }

      @java.lang.Override
      public com.gejian.pixel.proto.CommCompoundHeroRequestProtobuf.CommCompoundHeroRequest getDefaultInstanceForType() {
        return com.gejian.pixel.proto.CommCompoundHeroRequestProtobuf.CommCompoundHeroRequest.getDefaultInstance();
      }

      @java.lang.Override
      public com.gejian.pixel.proto.CommCompoundHeroRequestProtobuf.CommCompoundHeroRequest build() {
        com.gejian.pixel.proto.CommCompoundHeroRequestProtobuf.CommCompoundHeroRequest result = buildPartial();
        if (!result.isInitialized()) {
          throw newUninitializedMessageException(result);
        }
        return result;
      }

      @java.lang.Override
      public com.gejian.pixel.proto.CommCompoundHeroRequestProtobuf.CommCompoundHeroRequest buildPartial() {
        com.gejian.pixel.proto.CommCompoundHeroRequestProtobuf.CommCompoundHeroRequest result = new com.gejian.pixel.proto.CommCompoundHeroRequestProtobuf.CommCompoundHeroRequest(this);
        result.hero_ = hero_;
        onBuilt();
        return result;
      }

      @java.lang.Override
      public Builder clone() {
        return (Builder) super.clone();
      }
      @java.lang.Override
      public Builder setField(
          com.google.protobuf.Descriptors.FieldDescriptor field,
          java.lang.Object value) {
        return (Builder) super.setField(field, value);
      }
      @java.lang.Override
      public Builder clearField(
          com.google.protobuf.Descriptors.FieldDescriptor field) {
        return (Builder) super.clearField(field);
      }
      @java.lang.Override
      public Builder clearOneof(
          com.google.protobuf.Descriptors.OneofDescriptor oneof) {
        return (Builder) super.clearOneof(oneof);
      }
      @java.lang.Override
      public Builder setRepeatedField(
          com.google.protobuf.Descriptors.FieldDescriptor field,
          int index, java.lang.Object value) {
        return (Builder) super.setRepeatedField(field, index, value);
      }
      @java.lang.Override
      public Builder addRepeatedField(
          com.google.protobuf.Descriptors.FieldDescriptor field,
          java.lang.Object value) {
        return (Builder) super.addRepeatedField(field, value);
      }
      @java.lang.Override
      public Builder mergeFrom(com.google.protobuf.Message other) {
        if (other instanceof com.gejian.pixel.proto.CommCompoundHeroRequestProtobuf.CommCompoundHeroRequest) {
          return mergeFrom((com.gejian.pixel.proto.CommCompoundHeroRequestProtobuf.CommCompoundHeroRequest)other);
        } else {
          super.mergeFrom(other);
          return this;
        }
      }

      public Builder mergeFrom(com.gejian.pixel.proto.CommCompoundHeroRequestProtobuf.CommCompoundHeroRequest other) {
        if (other == com.gejian.pixel.proto.CommCompoundHeroRequestProtobuf.CommCompoundHeroRequest.getDefaultInstance()) return this;
        if (!other.getHero().isEmpty()) {
          hero_ = other.hero_;
          onChanged();
        }
        this.mergeUnknownFields(other.unknownFields);
        onChanged();
        return this;
      }

      @java.lang.Override
      public final boolean isInitialized() {
        return true;
      }

      @java.lang.Override
      public Builder mergeFrom(
          com.google.protobuf.CodedInputStream input,
          com.google.protobuf.ExtensionRegistryLite extensionRegistry)
          throws java.io.IOException {
        com.gejian.pixel.proto.CommCompoundHeroRequestProtobuf.CommCompoundHeroRequest parsedMessage = null;
        try {
          parsedMessage = PARSER.parsePartialFrom(input, extensionRegistry);
        } catch (com.google.protobuf.InvalidProtocolBufferException e) {
          parsedMessage = (com.gejian.pixel.proto.CommCompoundHeroRequestProtobuf.CommCompoundHeroRequest) e.getUnfinishedMessage();
          throw e.unwrapIOException();
        } finally {
          if (parsedMessage != null) {
            mergeFrom(parsedMessage);
          }
        }
        return this;
      }

      private java.lang.Object hero_ = "";
      /**
       * <code>string hero = 10;</code>
       */
      public java.lang.String getHero() {
        java.lang.Object ref = hero_;
        if (!(ref instanceof java.lang.String)) {
          com.google.protobuf.ByteString bs =
              (com.google.protobuf.ByteString) ref;
          java.lang.String s = bs.toStringUtf8();
          hero_ = s;
          return s;
        } else {
          return (java.lang.String) ref;
        }
      }
      /**
       * <code>string hero = 10;</code>
       */
      public com.google.protobuf.ByteString
          getHeroBytes() {
        java.lang.Object ref = hero_;
        if (ref instanceof String) {
          com.google.protobuf.ByteString b = 
              com.google.protobuf.ByteString.copyFromUtf8(
                  (java.lang.String) ref);
          hero_ = b;
          return b;
        } else {
          return (com.google.protobuf.ByteString) ref;
        }
      }
      /**
       * <code>string hero = 10;</code>
       */
      public Builder setHero(
          java.lang.String value) {
        if (value == null) {
    throw new NullPointerException();
  }
  
        hero_ = value;
        onChanged();
        return this;
      }
      /**
       * <code>string hero = 10;</code>
       */
      public Builder clearHero() {
        
        hero_ = getDefaultInstance().getHero();
        onChanged();
        return this;
      }
      /**
       * <code>string hero = 10;</code>
       */
      public Builder setHeroBytes(
          com.google.protobuf.ByteString value) {
        if (value == null) {
    throw new NullPointerException();
  }
  checkByteStringIsUtf8(value);
        
        hero_ = value;
        onChanged();
        return this;
      }
      @java.lang.Override
      public final Builder setUnknownFields(
          final com.google.protobuf.UnknownFieldSet unknownFields) {
        return super.setUnknownFieldsProto3(unknownFields);
      }

      @java.lang.Override
      public final Builder mergeUnknownFields(
          final com.google.protobuf.UnknownFieldSet unknownFields) {
        return super.mergeUnknownFields(unknownFields);
      }


      // @@protoc_insertion_point(builder_scope:CommCompoundHeroRequest)
    }

    // @@protoc_insertion_point(class_scope:CommCompoundHeroRequest)
    private static final com.gejian.pixel.proto.CommCompoundHeroRequestProtobuf.CommCompoundHeroRequest DEFAULT_INSTANCE;
    static {
      DEFAULT_INSTANCE = new com.gejian.pixel.proto.CommCompoundHeroRequestProtobuf.CommCompoundHeroRequest();
    }

    public static com.gejian.pixel.proto.CommCompoundHeroRequestProtobuf.CommCompoundHeroRequest getDefaultInstance() {
      return DEFAULT_INSTANCE;
    }

    private static final com.google.protobuf.Parser<CommCompoundHeroRequest>
        PARSER = new com.google.protobuf.AbstractParser<CommCompoundHeroRequest>() {
      @java.lang.Override
      public CommCompoundHeroRequest parsePartialFrom(
          com.google.protobuf.CodedInputStream input,
          com.google.protobuf.ExtensionRegistryLite extensionRegistry)
          throws com.google.protobuf.InvalidProtocolBufferException {
        return new CommCompoundHeroRequest(input, extensionRegistry);
      }
    };

    public static com.google.protobuf.Parser<CommCompoundHeroRequest> parser() {
      return PARSER;
    }

    @java.lang.Override
    public com.google.protobuf.Parser<CommCompoundHeroRequest> getParserForType() {
      return PARSER;
    }

    @java.lang.Override
    public com.gejian.pixel.proto.CommCompoundHeroRequestProtobuf.CommCompoundHeroRequest getDefaultInstanceForType() {
      return DEFAULT_INSTANCE;
    }

  }

  private static final com.google.protobuf.Descriptors.Descriptor
    internal_static_CommCompoundHeroRequest_descriptor;
  private static final 
    com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internal_static_CommCompoundHeroRequest_fieldAccessorTable;

  public static com.google.protobuf.Descriptors.FileDescriptor
      getDescriptor() {
    return descriptor;
  }
  private static  com.google.protobuf.Descriptors.FileDescriptor
      descriptor;
  static {
    java.lang.String[] descriptorData = {
      "\n\035CommCompoundHeroRequest.proto\"\'\n\027CommC" +
      "ompoundHeroRequest\022\014\n\004hero\030\n \001(\tB9\n\026com." +
      "gejian.pixel.protoB\037CommCompoundHeroRequ" +
      "estProtobufb\006proto3"
    };
    com.google.protobuf.Descriptors.FileDescriptor.InternalDescriptorAssigner assigner =
        new com.google.protobuf.Descriptors.FileDescriptor.    InternalDescriptorAssigner() {
          public com.google.protobuf.ExtensionRegistry assignDescriptors(
              com.google.protobuf.Descriptors.FileDescriptor root) {
            descriptor = root;
            return null;
          }
        };
    com.google.protobuf.Descriptors.FileDescriptor
      .internalBuildGeneratedFileFrom(descriptorData,
        new com.google.protobuf.Descriptors.FileDescriptor[] {
        }, assigner);
    internal_static_CommCompoundHeroRequest_descriptor =
      getDescriptor().getMessageTypes().get(0);
    internal_static_CommCompoundHeroRequest_fieldAccessorTable = new
      com.google.protobuf.GeneratedMessageV3.FieldAccessorTable(
        internal_static_CommCompoundHeroRequest_descriptor,
        new java.lang.String[] { "Hero", });
  }

  // @@protoc_insertion_point(outer_class_scope)
}
