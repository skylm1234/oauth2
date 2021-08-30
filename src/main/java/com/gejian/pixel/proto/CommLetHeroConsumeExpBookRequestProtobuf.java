// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: CommLetHeroConsumeExpBookRequest.proto

package com.gejian.pixel.proto;

public final class CommLetHeroConsumeExpBookRequestProtobuf {
  private CommLetHeroConsumeExpBookRequestProtobuf() {}
  public static void registerAllExtensions(
      com.google.protobuf.ExtensionRegistryLite registry) {
  }

  public static void registerAllExtensions(
      com.google.protobuf.ExtensionRegistry registry) {
    registerAllExtensions(
        (com.google.protobuf.ExtensionRegistryLite) registry);
  }
  public interface CommLetHeroConsumeExpBookRequestOrBuilder extends
      // @@protoc_insertion_point(interface_extends:CommLetHeroConsumeExpBookRequest)
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

    /**
     * <code>repeated string books = 11;</code>
     */
    java.util.List<java.lang.String>
        getBooksList();
    /**
     * <code>repeated string books = 11;</code>
     */
    int getBooksCount();
    /**
     * <code>repeated string books = 11;</code>
     */
    java.lang.String getBooks(int index);
    /**
     * <code>repeated string books = 11;</code>
     */
    com.google.protobuf.ByteString
        getBooksBytes(int index);
  }
  /**
   * Protobuf type {@code CommLetHeroConsumeExpBookRequest}
   */
  public  static final class CommLetHeroConsumeExpBookRequest extends
      com.google.protobuf.GeneratedMessageV3 implements
      // @@protoc_insertion_point(message_implements:CommLetHeroConsumeExpBookRequest)
      CommLetHeroConsumeExpBookRequestOrBuilder {
  private static final long serialVersionUID = 0L;
    // Use CommLetHeroConsumeExpBookRequest.newBuilder() to construct.
    private CommLetHeroConsumeExpBookRequest(com.google.protobuf.GeneratedMessageV3.Builder<?> builder) {
      super(builder);
    }
    private CommLetHeroConsumeExpBookRequest() {
      hero_ = "";
      books_ = com.google.protobuf.LazyStringArrayList.EMPTY;
    }

    @java.lang.Override
    public final com.google.protobuf.UnknownFieldSet
    getUnknownFields() {
      return this.unknownFields;
    }
    private CommLetHeroConsumeExpBookRequest(
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
            case 90: {
              java.lang.String s = input.readStringRequireUtf8();
              if (!((mutable_bitField0_ & 0x00000002) == 0x00000002)) {
                books_ = new com.google.protobuf.LazyStringArrayList();
                mutable_bitField0_ |= 0x00000002;
              }
              books_.add(s);
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
        if (((mutable_bitField0_ & 0x00000002) == 0x00000002)) {
          books_ = books_.getUnmodifiableView();
        }
        this.unknownFields = unknownFields.build();
        makeExtensionsImmutable();
      }
    }
    public static final com.google.protobuf.Descriptors.Descriptor
        getDescriptor() {
      return com.gejian.pixel.proto.CommLetHeroConsumeExpBookRequestProtobuf.internal_static_CommLetHeroConsumeExpBookRequest_descriptor;
    }

    @java.lang.Override
    protected com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
        internalGetFieldAccessorTable() {
      return com.gejian.pixel.proto.CommLetHeroConsumeExpBookRequestProtobuf.internal_static_CommLetHeroConsumeExpBookRequest_fieldAccessorTable
          .ensureFieldAccessorsInitialized(
              com.gejian.pixel.proto.CommLetHeroConsumeExpBookRequestProtobuf.CommLetHeroConsumeExpBookRequest.class, com.gejian.pixel.proto.CommLetHeroConsumeExpBookRequestProtobuf.CommLetHeroConsumeExpBookRequest.Builder.class);
    }

    private int bitField0_;
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

    public static final int BOOKS_FIELD_NUMBER = 11;
    private com.google.protobuf.LazyStringList books_;
    /**
     * <code>repeated string books = 11;</code>
     */
    public com.google.protobuf.ProtocolStringList
        getBooksList() {
      return books_;
    }
    /**
     * <code>repeated string books = 11;</code>
     */
    public int getBooksCount() {
      return books_.size();
    }
    /**
     * <code>repeated string books = 11;</code>
     */
    public java.lang.String getBooks(int index) {
      return books_.get(index);
    }
    /**
     * <code>repeated string books = 11;</code>
     */
    public com.google.protobuf.ByteString
        getBooksBytes(int index) {
      return books_.getByteString(index);
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
      for (int i = 0; i < books_.size(); i++) {
        com.google.protobuf.GeneratedMessageV3.writeString(output, 11, books_.getRaw(i));
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
      {
        int dataSize = 0;
        for (int i = 0; i < books_.size(); i++) {
          dataSize += computeStringSizeNoTag(books_.getRaw(i));
        }
        size += dataSize;
        size += 1 * getBooksList().size();
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
      if (!(obj instanceof com.gejian.pixel.proto.CommLetHeroConsumeExpBookRequestProtobuf.CommLetHeroConsumeExpBookRequest)) {
        return super.equals(obj);
      }
      com.gejian.pixel.proto.CommLetHeroConsumeExpBookRequestProtobuf.CommLetHeroConsumeExpBookRequest other = (com.gejian.pixel.proto.CommLetHeroConsumeExpBookRequestProtobuf.CommLetHeroConsumeExpBookRequest) obj;

      boolean result = true;
      result = result && getHero()
          .equals(other.getHero());
      result = result && getBooksList()
          .equals(other.getBooksList());
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
      if (getBooksCount() > 0) {
        hash = (37 * hash) + BOOKS_FIELD_NUMBER;
        hash = (53 * hash) + getBooksList().hashCode();
      }
      hash = (29 * hash) + unknownFields.hashCode();
      memoizedHashCode = hash;
      return hash;
    }

    public static com.gejian.pixel.proto.CommLetHeroConsumeExpBookRequestProtobuf.CommLetHeroConsumeExpBookRequest parseFrom(
        java.nio.ByteBuffer data)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return PARSER.parseFrom(data);
    }
    public static com.gejian.pixel.proto.CommLetHeroConsumeExpBookRequestProtobuf.CommLetHeroConsumeExpBookRequest parseFrom(
        java.nio.ByteBuffer data,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return PARSER.parseFrom(data, extensionRegistry);
    }
    public static com.gejian.pixel.proto.CommLetHeroConsumeExpBookRequestProtobuf.CommLetHeroConsumeExpBookRequest parseFrom(
        com.google.protobuf.ByteString data)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return PARSER.parseFrom(data);
    }
    public static com.gejian.pixel.proto.CommLetHeroConsumeExpBookRequestProtobuf.CommLetHeroConsumeExpBookRequest parseFrom(
        com.google.protobuf.ByteString data,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return PARSER.parseFrom(data, extensionRegistry);
    }
    public static com.gejian.pixel.proto.CommLetHeroConsumeExpBookRequestProtobuf.CommLetHeroConsumeExpBookRequest parseFrom(byte[] data)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return PARSER.parseFrom(data);
    }
    public static com.gejian.pixel.proto.CommLetHeroConsumeExpBookRequestProtobuf.CommLetHeroConsumeExpBookRequest parseFrom(
        byte[] data,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return PARSER.parseFrom(data, extensionRegistry);
    }
    public static com.gejian.pixel.proto.CommLetHeroConsumeExpBookRequestProtobuf.CommLetHeroConsumeExpBookRequest parseFrom(java.io.InputStream input)
        throws java.io.IOException {
      return com.google.protobuf.GeneratedMessageV3
          .parseWithIOException(PARSER, input);
    }
    public static com.gejian.pixel.proto.CommLetHeroConsumeExpBookRequestProtobuf.CommLetHeroConsumeExpBookRequest parseFrom(
        java.io.InputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws java.io.IOException {
      return com.google.protobuf.GeneratedMessageV3
          .parseWithIOException(PARSER, input, extensionRegistry);
    }
    public static com.gejian.pixel.proto.CommLetHeroConsumeExpBookRequestProtobuf.CommLetHeroConsumeExpBookRequest parseDelimitedFrom(java.io.InputStream input)
        throws java.io.IOException {
      return com.google.protobuf.GeneratedMessageV3
          .parseDelimitedWithIOException(PARSER, input);
    }
    public static com.gejian.pixel.proto.CommLetHeroConsumeExpBookRequestProtobuf.CommLetHeroConsumeExpBookRequest parseDelimitedFrom(
        java.io.InputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws java.io.IOException {
      return com.google.protobuf.GeneratedMessageV3
          .parseDelimitedWithIOException(PARSER, input, extensionRegistry);
    }
    public static com.gejian.pixel.proto.CommLetHeroConsumeExpBookRequestProtobuf.CommLetHeroConsumeExpBookRequest parseFrom(
        com.google.protobuf.CodedInputStream input)
        throws java.io.IOException {
      return com.google.protobuf.GeneratedMessageV3
          .parseWithIOException(PARSER, input);
    }
    public static com.gejian.pixel.proto.CommLetHeroConsumeExpBookRequestProtobuf.CommLetHeroConsumeExpBookRequest parseFrom(
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
    public static Builder newBuilder(com.gejian.pixel.proto.CommLetHeroConsumeExpBookRequestProtobuf.CommLetHeroConsumeExpBookRequest prototype) {
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
     * Protobuf type {@code CommLetHeroConsumeExpBookRequest}
     */
    public static final class Builder extends
        com.google.protobuf.GeneratedMessageV3.Builder<Builder> implements
        // @@protoc_insertion_point(builder_implements:CommLetHeroConsumeExpBookRequest)
        com.gejian.pixel.proto.CommLetHeroConsumeExpBookRequestProtobuf.CommLetHeroConsumeExpBookRequestOrBuilder {
      public static final com.google.protobuf.Descriptors.Descriptor
          getDescriptor() {
        return com.gejian.pixel.proto.CommLetHeroConsumeExpBookRequestProtobuf.internal_static_CommLetHeroConsumeExpBookRequest_descriptor;
      }

      @java.lang.Override
      protected com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
          internalGetFieldAccessorTable() {
        return com.gejian.pixel.proto.CommLetHeroConsumeExpBookRequestProtobuf.internal_static_CommLetHeroConsumeExpBookRequest_fieldAccessorTable
            .ensureFieldAccessorsInitialized(
                com.gejian.pixel.proto.CommLetHeroConsumeExpBookRequestProtobuf.CommLetHeroConsumeExpBookRequest.class, com.gejian.pixel.proto.CommLetHeroConsumeExpBookRequestProtobuf.CommLetHeroConsumeExpBookRequest.Builder.class);
      }

      // Construct using com.gejian.pixel.proto.CommLetHeroConsumeExpBookRequestProtobuf.CommLetHeroConsumeExpBookRequest.newBuilder()
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

        books_ = com.google.protobuf.LazyStringArrayList.EMPTY;
        bitField0_ = (bitField0_ & ~0x00000002);
        return this;
      }

      @java.lang.Override
      public com.google.protobuf.Descriptors.Descriptor
          getDescriptorForType() {
        return com.gejian.pixel.proto.CommLetHeroConsumeExpBookRequestProtobuf.internal_static_CommLetHeroConsumeExpBookRequest_descriptor;
      }

      @java.lang.Override
      public com.gejian.pixel.proto.CommLetHeroConsumeExpBookRequestProtobuf.CommLetHeroConsumeExpBookRequest getDefaultInstanceForType() {
        return com.gejian.pixel.proto.CommLetHeroConsumeExpBookRequestProtobuf.CommLetHeroConsumeExpBookRequest.getDefaultInstance();
      }

      @java.lang.Override
      public com.gejian.pixel.proto.CommLetHeroConsumeExpBookRequestProtobuf.CommLetHeroConsumeExpBookRequest build() {
        com.gejian.pixel.proto.CommLetHeroConsumeExpBookRequestProtobuf.CommLetHeroConsumeExpBookRequest result = buildPartial();
        if (!result.isInitialized()) {
          throw newUninitializedMessageException(result);
        }
        return result;
      }

      @java.lang.Override
      public com.gejian.pixel.proto.CommLetHeroConsumeExpBookRequestProtobuf.CommLetHeroConsumeExpBookRequest buildPartial() {
        com.gejian.pixel.proto.CommLetHeroConsumeExpBookRequestProtobuf.CommLetHeroConsumeExpBookRequest result = new com.gejian.pixel.proto.CommLetHeroConsumeExpBookRequestProtobuf.CommLetHeroConsumeExpBookRequest(this);
        int from_bitField0_ = bitField0_;
        int to_bitField0_ = 0;
        result.hero_ = hero_;
        if (((bitField0_ & 0x00000002) == 0x00000002)) {
          books_ = books_.getUnmodifiableView();
          bitField0_ = (bitField0_ & ~0x00000002);
        }
        result.books_ = books_;
        result.bitField0_ = to_bitField0_;
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
        if (other instanceof com.gejian.pixel.proto.CommLetHeroConsumeExpBookRequestProtobuf.CommLetHeroConsumeExpBookRequest) {
          return mergeFrom((com.gejian.pixel.proto.CommLetHeroConsumeExpBookRequestProtobuf.CommLetHeroConsumeExpBookRequest)other);
        } else {
          super.mergeFrom(other);
          return this;
        }
      }

      public Builder mergeFrom(com.gejian.pixel.proto.CommLetHeroConsumeExpBookRequestProtobuf.CommLetHeroConsumeExpBookRequest other) {
        if (other == com.gejian.pixel.proto.CommLetHeroConsumeExpBookRequestProtobuf.CommLetHeroConsumeExpBookRequest.getDefaultInstance()) return this;
        if (!other.getHero().isEmpty()) {
          hero_ = other.hero_;
          onChanged();
        }
        if (!other.books_.isEmpty()) {
          if (books_.isEmpty()) {
            books_ = other.books_;
            bitField0_ = (bitField0_ & ~0x00000002);
          } else {
            ensureBooksIsMutable();
            books_.addAll(other.books_);
          }
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
        com.gejian.pixel.proto.CommLetHeroConsumeExpBookRequestProtobuf.CommLetHeroConsumeExpBookRequest parsedMessage = null;
        try {
          parsedMessage = PARSER.parsePartialFrom(input, extensionRegistry);
        } catch (com.google.protobuf.InvalidProtocolBufferException e) {
          parsedMessage = (com.gejian.pixel.proto.CommLetHeroConsumeExpBookRequestProtobuf.CommLetHeroConsumeExpBookRequest) e.getUnfinishedMessage();
          throw e.unwrapIOException();
        } finally {
          if (parsedMessage != null) {
            mergeFrom(parsedMessage);
          }
        }
        return this;
      }
      private int bitField0_;

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

      private com.google.protobuf.LazyStringList books_ = com.google.protobuf.LazyStringArrayList.EMPTY;
      private void ensureBooksIsMutable() {
        if (!((bitField0_ & 0x00000002) == 0x00000002)) {
          books_ = new com.google.protobuf.LazyStringArrayList(books_);
          bitField0_ |= 0x00000002;
         }
      }
      /**
       * <code>repeated string books = 11;</code>
       */
      public com.google.protobuf.ProtocolStringList
          getBooksList() {
        return books_.getUnmodifiableView();
      }
      /**
       * <code>repeated string books = 11;</code>
       */
      public int getBooksCount() {
        return books_.size();
      }
      /**
       * <code>repeated string books = 11;</code>
       */
      public java.lang.String getBooks(int index) {
        return books_.get(index);
      }
      /**
       * <code>repeated string books = 11;</code>
       */
      public com.google.protobuf.ByteString
          getBooksBytes(int index) {
        return books_.getByteString(index);
      }
      /**
       * <code>repeated string books = 11;</code>
       */
      public Builder setBooks(
          int index, java.lang.String value) {
        if (value == null) {
    throw new NullPointerException();
  }
  ensureBooksIsMutable();
        books_.set(index, value);
        onChanged();
        return this;
      }
      /**
       * <code>repeated string books = 11;</code>
       */
      public Builder addBooks(
          java.lang.String value) {
        if (value == null) {
    throw new NullPointerException();
  }
  ensureBooksIsMutable();
        books_.add(value);
        onChanged();
        return this;
      }
      /**
       * <code>repeated string books = 11;</code>
       */
      public Builder addAllBooks(
          java.lang.Iterable<java.lang.String> values) {
        ensureBooksIsMutable();
        com.google.protobuf.AbstractMessageLite.Builder.addAll(
            values, books_);
        onChanged();
        return this;
      }
      /**
       * <code>repeated string books = 11;</code>
       */
      public Builder clearBooks() {
        books_ = com.google.protobuf.LazyStringArrayList.EMPTY;
        bitField0_ = (bitField0_ & ~0x00000002);
        onChanged();
        return this;
      }
      /**
       * <code>repeated string books = 11;</code>
       */
      public Builder addBooksBytes(
          com.google.protobuf.ByteString value) {
        if (value == null) {
    throw new NullPointerException();
  }
  checkByteStringIsUtf8(value);
        ensureBooksIsMutable();
        books_.add(value);
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


      // @@protoc_insertion_point(builder_scope:CommLetHeroConsumeExpBookRequest)
    }

    // @@protoc_insertion_point(class_scope:CommLetHeroConsumeExpBookRequest)
    private static final com.gejian.pixel.proto.CommLetHeroConsumeExpBookRequestProtobuf.CommLetHeroConsumeExpBookRequest DEFAULT_INSTANCE;
    static {
      DEFAULT_INSTANCE = new com.gejian.pixel.proto.CommLetHeroConsumeExpBookRequestProtobuf.CommLetHeroConsumeExpBookRequest();
    }

    public static com.gejian.pixel.proto.CommLetHeroConsumeExpBookRequestProtobuf.CommLetHeroConsumeExpBookRequest getDefaultInstance() {
      return DEFAULT_INSTANCE;
    }

    private static final com.google.protobuf.Parser<CommLetHeroConsumeExpBookRequest>
        PARSER = new com.google.protobuf.AbstractParser<CommLetHeroConsumeExpBookRequest>() {
      @java.lang.Override
      public CommLetHeroConsumeExpBookRequest parsePartialFrom(
          com.google.protobuf.CodedInputStream input,
          com.google.protobuf.ExtensionRegistryLite extensionRegistry)
          throws com.google.protobuf.InvalidProtocolBufferException {
        return new CommLetHeroConsumeExpBookRequest(input, extensionRegistry);
      }
    };

    public static com.google.protobuf.Parser<CommLetHeroConsumeExpBookRequest> parser() {
      return PARSER;
    }

    @java.lang.Override
    public com.google.protobuf.Parser<CommLetHeroConsumeExpBookRequest> getParserForType() {
      return PARSER;
    }

    @java.lang.Override
    public com.gejian.pixel.proto.CommLetHeroConsumeExpBookRequestProtobuf.CommLetHeroConsumeExpBookRequest getDefaultInstanceForType() {
      return DEFAULT_INSTANCE;
    }

  }

  private static final com.google.protobuf.Descriptors.Descriptor
    internal_static_CommLetHeroConsumeExpBookRequest_descriptor;
  private static final 
    com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internal_static_CommLetHeroConsumeExpBookRequest_fieldAccessorTable;

  public static com.google.protobuf.Descriptors.FileDescriptor
      getDescriptor() {
    return descriptor;
  }
  private static  com.google.protobuf.Descriptors.FileDescriptor
      descriptor;
  static {
    java.lang.String[] descriptorData = {
      "\n&CommLetHeroConsumeExpBookRequest.proto" +
      "\"?\n CommLetHeroConsumeExpBookRequest\022\014\n\004" +
      "hero\030\n \001(\t\022\r\n\005books\030\013 \003(\tBB\n\026com.gejian." +
      "pixel.protoB(CommLetHeroConsumeExpBookRe" +
      "questProtobufb\006proto3"
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
    internal_static_CommLetHeroConsumeExpBookRequest_descriptor =
      getDescriptor().getMessageTypes().get(0);
    internal_static_CommLetHeroConsumeExpBookRequest_fieldAccessorTable = new
      com.google.protobuf.GeneratedMessageV3.FieldAccessorTable(
        internal_static_CommLetHeroConsumeExpBookRequest_descriptor,
        new java.lang.String[] { "Hero", "Books", });
  }

  // @@protoc_insertion_point(outer_class_scope)
}
